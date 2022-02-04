package com.jaime.platform.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Code {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column
    private LocalDateTime date;

    @Column
    private boolean secret;

    @Column
    private long views;

    @Column
    private boolean viewRestricted;

    @Column
    private long time;

    @Column
    private boolean timeRestricted;

    public Code(String code) {
        this.date = LocalDateTime.now();
        this.code = code;
    }

    public boolean isNotSecret() {
        return !secret;
    }

    public void decrementViews() {
       this.views = this.views - 1;
    }

    public String getDateString(){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(this.date);
    }

}