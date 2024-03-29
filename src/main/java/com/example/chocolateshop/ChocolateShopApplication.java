package com.example.chocolateshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class ChocolateShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChocolateShopApplication.class, args);
    }

}
