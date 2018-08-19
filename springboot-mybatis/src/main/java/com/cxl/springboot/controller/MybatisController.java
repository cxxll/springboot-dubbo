package com.cxl.springboot.controller;

import com.cxl.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class MybatisController {

    @Autowired
    private UserService userService;

    @GetMapping("/boot/users")
    public Object users(){

        //线程，该线程调用底层查询所有学生的方法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                userService.getAllUser();
            }
        };

        //多线程测试一下缓存穿透问题
        ExecutorService executorService = Executors.newFixedThreadPool(25);
        for(int i =0;i<10000;i++){
            executorService.submit(runnable);
        }


        return userService.getAllUser();
    }

    @GetMapping("/boot/usersupdate")
    public Object update(){


        return userService.update();
    }
}
