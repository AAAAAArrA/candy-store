package com.example.chocolateshop.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;

@Configuration
public class IntegrationConfig {
    @Bean("bean")
    public DirectChannel channel(){
        return  new DirectChannel();
    }
}
