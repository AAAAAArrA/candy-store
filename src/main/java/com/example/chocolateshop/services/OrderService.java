package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void save(Order order);
    List<Order> getOrderByUser(String name);
    List<Order> getAllOrders();

}
