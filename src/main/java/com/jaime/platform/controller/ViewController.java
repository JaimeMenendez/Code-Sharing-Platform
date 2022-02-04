package com.jaime.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import  com.jaime.platform.dto.CodeDto;
import  com.jaime.platform.service.CodeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class ViewController {
    private final CodeService codeService;

    public ViewController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/code/{id}")
    public String getHtmlCode(@PathVariable UUID id, Model model) {
        model.addAttribute("code", codeService.getCode(id));
        return "Code";
    }

    @GetMapping(value = "/code/new")
    public String createHtmlCode() {
        return "CreateCode";
    }

    @GetMapping(value = "/code/latest")
    public String getLastCodes(Model model) {
        List<CodeDto> list = codeService.latestCodes().stream().map(CodeDto::new).collect(Collectors.toList());
        model.addAttribute("codes",list);
        return "LastCodes";
    }
}
