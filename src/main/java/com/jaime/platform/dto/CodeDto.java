package com.jaime.platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import  com.jaime.platform.model.Code;

import javax.validation.constraints.NotBlank;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDto {
    @NotBlank(message = "Code must be not black or null!")
    private String code;
    private String date;
    private long views;
    private long time;

    public CodeDto(Code code) {
        if (code == null) {
            this.code = "";
            this.date = "";
        } else {
            if (code.getCode() == null) {
                this.code = "";
            } else {
                this.code = code.getCode();
            }
            if (code.getDate() == null) {
                this.date = "";
            } else {
                this.date = code.getDateString();
                this.views = code.getViews();
                this.time = code.getTime();
            }
        }
    }

    public boolean secret() {
        return views > 0 || time > 0;
    }

    public boolean viewRestricted() {
        return views > 0;
    }

    public boolean timeRestricted() {
        return time > 0;
    }
}
