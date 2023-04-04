package com.coca.shoppinggateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ShoppingGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingGatewayApplication.class, args);
    }

}
