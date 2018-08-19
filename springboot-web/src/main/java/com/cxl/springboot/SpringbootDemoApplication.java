package com.cxl.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemoApplication {

	public static void main(String[] args) {
		//启动了springboot程序，启动spring容器，启动内嵌的tomcat
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}
}
