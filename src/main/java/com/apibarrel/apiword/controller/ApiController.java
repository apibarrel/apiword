package com.apibarrel.apiword.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.models.Swagger;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.ObjectProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.parser.SwaggerParser;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @RequestMapping("/word")
    public  String downWord(){
        String swaggerString = null;
        try {
            swaggerString = IOUtils.toString(new FileInputStream(new File("D:/pet.yaml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Swagger swagger = new SwaggerParser().parse(swaggerString);
        Map<String, List<Swagger>> map = new HashMap<>();
        List<Swagger> list = new ArrayList<>();
        list.add(swagger);
        map.put("swaggers",list);
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(this.getClass(),"/template");
        configuration.setDefaultEncoding("utf-8");
        try {
            Template template = configuration.getTemplate("word.html");
            FileWriter writer = new FileWriter(new File("d:/api.doc"));
            try {
                template.process(map,writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "sss";
    }
}
