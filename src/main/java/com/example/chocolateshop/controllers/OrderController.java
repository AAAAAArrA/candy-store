package com.example.chocolateshop.controllers;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.repositories.OrderDetailsRepository;
import com.example.chocolateshop.services.OrderDetailsService;
import com.example.chocolateshop.services.OrderServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceImpl orderService;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsService orderDetailsService;
    public OrderController(OrderServiceImpl orderService, OrderDetailsRepository orderDetailsRepository, OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public String orders(Model model, Principal principal) {
        List<Order> orders = orderService.getOrderByUser(principal.getName());
        model.addAttribute("order", orders);
        model.addAttribute("details", orderDetailsRepository.findAll());
        return "orderList";


    }
}
