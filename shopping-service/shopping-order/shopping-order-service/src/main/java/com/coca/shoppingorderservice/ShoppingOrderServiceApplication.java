package com.coca.shoppingorderservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ShoppingOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingOrderServiceApplication.class, args);
    }

}
