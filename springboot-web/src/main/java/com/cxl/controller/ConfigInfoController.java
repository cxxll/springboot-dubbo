package com.cxl.controller;


import com.cxl.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigInfoController {

    @Value("${boot.name}")
    private String name;

    @Value("${boot.location}")
    private String location;

    @Autowired
    private ConfigInfo configInfo;

    @RequestMapping("/boot/config")
    public String Config(){
        return name + "---" +location;
    }
}
