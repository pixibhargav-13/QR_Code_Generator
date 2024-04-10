package com.bhargav.qr_code_generator.service;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemplateService {

    @Autowired
    private Configuration freemarkerConfig;

    public String processTemplate(String username) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate("template_1.ftl");

        Map<String, Object> model = new HashMap<>();
        model.put("username", username);

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
    }
}
