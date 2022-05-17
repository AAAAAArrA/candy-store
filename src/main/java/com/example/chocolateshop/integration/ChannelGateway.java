package com.example.chocolateshop.integration;


import com.example.chocolateshop.models.Chocolate;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ChannelGateway {
    @Gateway(replyChannel = "channel")
    void process(Chocolate chocolate);
}
