package com.jaime.platform.service;

import org.springframework.stereotype.Service;
import  com.jaime.platform.error.CodeNotFoundException;
import  com.jaime.platform.model.Code;
import  com.jaime.platform.repository.CodeRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code getCode(UUID id) {
        Code code = codeRepository.findById(id).orElseThrow(CodeNotFoundException::new);
        if (code.isNotSecret()) {
            return code;
        }
        LocalDateTime currentTime = LocalDateTime.now();
        long difference = Duration.between(code.getDate(), currentTime).getSeconds();

        boolean timeExpired = code.isTimeRestricted() && code.getTime() <= difference;
        boolean viewExpired = code.isViewRestricted() && code.getViews() <= 0;

        if (code.isViewRestricted() && !viewExpired) {
            code.decrementViews();
        }

        if (timeExpired || viewExpired) {
            codeRepository.delete(code);
            throw new CodeNotFoundException();
        }

        codeRepository.save(code);
        if (code.isTimeRestricted()) {
            code.setTime(code.getTime() - difference);
        }
        return code;
    }

    public UUID saveCode(Code code) {
        Code codeSaved = codeRepository.save(code);
        return codeSaved.getId();
    }

    public List<Code> latestCodes() {
        return codeRepository.findTop10BySecretOrderByDateDesc(false);
    }
}
