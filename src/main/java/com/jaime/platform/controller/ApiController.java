package com.jaime.platform.controller;

import com.jaime.platform.dto.CodeDto;
import com.jaime.platform.model.Code;
import com.jaime.platform.service.CodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ApiController {
    private final CodeService codeService;

    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/api/code/{id}")
    public ResponseEntity<?> getCode(@PathVariable UUID id) {
        return ResponseEntity.ok(new CodeDto(codeService.getCode(id)));
    }

    @PostMapping(value = "/api/code/new")
    public ResponseEntity<?> saveNewCode(@RequestBody CodeDto code) {
        Code newCode = Code.builder()
                .code(code.getCode())
                .time(code.getTime())
                .views(code.getViews())
                .secret(code.secret())
                .viewRestricted(code.viewRestricted())
                .timeRestricted(code.timeRestricted())
                .date(LocalDateTime.now())
                .build();

        UUID id = codeService.saveCode(newCode);
        return ResponseEntity.ok(Map.of("id", id.toString()));
    }

    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<?> getLatestCodes() {
        List<CodeDto> list = codeService.latestCodes().stream().map(CodeDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
