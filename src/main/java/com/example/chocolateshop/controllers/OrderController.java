package com.example.chocolateshop.controllers;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.services.OrderDetailsService;
import com.example.chocolateshop.services.OrderService;
import com.example.chocolateshop.services.implementation.ProductServiceImpl;
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
    private final ProductServiceImpl productService;

    public OrderController(OrderService orderService, OrderDetailsService orderDetailsService, ProductServiceImpl productService) {
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
        this.productService = productService;
    }

    @GetMapping
    public String orders(Model model, Principal principal) {
        List<Order> orders = orderService.getOrderByUser(principal.getName());
        model.addAttribute("order", orders);
        model.addAttribute("details", orderDetailsService.getDetails());
        return "orderList";
    }
    @GetMapping("/report-1")
    public String bucket(Model model){
        model.addAttribute("firstReport", orderService.getAllOrders());
        model.addAttribute("chocolates", productService.getAll());
        return "report-1";

    }
}
