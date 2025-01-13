package com.jobease.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JobeaseApplication {
	@RequestMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
	public static void main(String[] args) {
		SpringApplication.run(JobeaseApplication.class, args);
	}

}
