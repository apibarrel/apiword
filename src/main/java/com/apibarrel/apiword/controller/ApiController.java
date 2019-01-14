package com.apibarrel.apiword.controller;

import com.apibarrel.apiword.model.ApiAsset;
import com.apibarrel.apiword.model.ApiBase;
import com.apibarrel.apiword.service.ApiAssetService;
import com.apibarrel.apiword.service.ApiBaseService;
import com.apibarrel.apiword.utils.UUIDUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @Autowired
    private ApiBaseService apiBaseService;

    @Autowired
    private ApiAssetService apiAssetService;

    /**
     * @param file
     * @param name
     * @param version
     * @param namespace
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createApi(@RequestParam("file") MultipartFile file, @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "version", required = false) String version,
                            @RequestParam(value = "namespace", required = false) String namespace,
                            @RequestParam(value = "protocol", required = false) String protocol) {
        String swaggerString = null;
        try {
            swaggerString = IOUtils.toString(file.getInputStream());
        } catch (IOException e) {
            //TODO handle the parse exception
            throw new RuntimeException("read file failed.");
        }
        String apiId = UUIDUtils.getUUID();
        ApiBase apiBase = new ApiBase();
        apiBase.setId(apiId).setCreateTime(String.valueOf(System.currentTimeMillis()))
                .setCreator("").setName(name).setNamespace(namespace).setProtocol(protocol)
                .setVersion(version).setTag("");
        apiBaseService.insert(apiBase);

        String assetId = UUIDUtils.getUUID();
        ApiAsset apiAsset = new ApiAsset();
        apiAsset.setApiId(apiId).setId(assetId).setName(file.getOriginalFilename())
                .setContent(swaggerString).setType("METADATA");
        apiAssetService.insert(apiAsset);
        return assetId;
    }

    @RequestMapping(value = "/{id}/download/word", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadWord(@PathParam("id") String id) {
        ApiAsset apiAsset = apiAssetService.query(id);
        Swagger swagger = new SwaggerParser().parse(apiAsset.getContent());
        Map<String, List<Swagger>> map = new HashMap<>();
        List<Swagger> list = new ArrayList<>();
        list.add(swagger);
        map.put("swaggers", list);
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(this.getClass(), "/template");
        configuration.setDefaultEncoding("utf-8");
        File file = new File("api.doc");
        try {
            Template template = configuration.getTemplate("word.html");
            FileWriter writer = new FileWriter(file);
            try {
                template.process(map, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("create word failed.");
        } finally {
            file.delete();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + file.getName());
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException("create word failed.");
        }
    }
}
