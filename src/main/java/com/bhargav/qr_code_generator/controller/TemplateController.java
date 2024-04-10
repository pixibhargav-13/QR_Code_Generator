package com.bhargav.qr_code_generator.controller;

import com.bhargav.qr_code_generator.service.TemplateService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @GetMapping("/generate-template")
    public String generateTemplate(@RequestParam String username) {
        try {
            return templateService.processTemplate(username);
        } catch (IOException e) {
            return "Error processing template";
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
