package com.cxl.controller;


import com.cxl.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MVCController {


    @RequestMapping("/boot/getUser")
    public Object  getUser(){
        User user = new User();
        user.setId(100);
        user.setName("测试");
        user.setPassword("121212");
        return user;
    }
}
