package com.cxl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hellocontroller {

    @RequestMapping("/boot/hello")
    public String Hello(){
        return "Hello Spring Boot";
    }
}
