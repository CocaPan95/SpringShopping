package com.coca.shoppinguserservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ShoppingUserServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShoppingUserServiceApplication.class, args);

    }

}
