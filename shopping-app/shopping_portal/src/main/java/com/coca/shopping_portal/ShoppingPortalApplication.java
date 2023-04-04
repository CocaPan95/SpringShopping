package com.coca.shopping_portal;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDubbo
@EnableFeignClients
@SpringBootApplication
public class ShoppingPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingPortalApplication.class, args);
    }

}
