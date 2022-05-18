package com.example.chocolateshop.controllers;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.services.OrderDetailsService;
import com.example.chocolateshop.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;

    public OrderController(OrderService orderService, OrderDetailsService orderDetailsService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public String orders(Model model, Principal principal) {
        List<Order> orders = orderService.getOrderByUser(principal.getName());
        model.addAttribute("order", orders);
        model.addAttribute("details", orderDetailsService.getDetails());
        return "orderList";


    }
}
