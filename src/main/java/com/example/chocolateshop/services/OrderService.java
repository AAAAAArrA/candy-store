package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    void save(Order order);
    List<Order> getOrderByUser(String name);
    List<Order> getAllOrders();
    Page<Order> getPaginatedOrdersBuUser(String name, int pageNo, int pageSize);
    public Page<Order> getAllPaginatedOrders(int pageNo, int pageSize);

}
