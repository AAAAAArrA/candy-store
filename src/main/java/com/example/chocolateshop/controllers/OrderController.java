package com.example.chocolateshop.controllers;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.services.OrderDetailsService;
import com.example.chocolateshop.services.OrderService;
import com.example.chocolateshop.services.implementation.ProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/page/{pageNo}")
    public String findPaginatedOrders(@PathVariable (value = "pageNo") int pageNo,
                                      Model model, Principal principal){
        int pageSize = 30;
        Page<Order> page = orderService.getPaginatedOrdersBuUser(principal.getName(),
                pageNo, pageSize);
        List<Order> orderList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("order", orderList);
        return "orderList";
    }
    @GetMapping("/report-1/{pageNo}")
    public String getPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 20;
        Page<Order> page = orderService.getAllPaginatedOrders(pageNo, pageSize);
        List<Order> orders = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("orderList", orders);
        return "report-1";
    }



}
