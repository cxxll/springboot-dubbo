package com.cxl.controller;

import com.cxl.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulController {

    @RequestMapping("/boot/user/{id}")
    public Object user(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(3);
        user.setName("123333");
        user.setPassword("121212");
        return user;
    }

    @RequestMapping("/boot/user/{id}/{name}")
    public Object user(@PathVariable("id") Integer id,@PathVariable("name") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }
}
