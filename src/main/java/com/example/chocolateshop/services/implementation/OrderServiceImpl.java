package com.example.chocolateshop.services.implementation;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.OrderDetailsRepository;
import com.example.chocolateshop.repositories.OrderRepository;
import com.example.chocolateshop.repositories.UserRepository;
import com.example.chocolateshop.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }
    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
    @Override
    public List<Order> getOrderByUser(String name) {
        User user = userRepository.findByFullName(name);
        List<Order> orderList = orderRepository.findAllByUserId(user.getId());
        return orderList;
    }





}
