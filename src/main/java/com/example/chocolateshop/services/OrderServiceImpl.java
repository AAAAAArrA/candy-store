package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.dto.BucketDetailsDTO;
import com.example.chocolateshop.dto.OrderDTO;
import com.example.chocolateshop.dto.OrderDetailsDTO;
import com.example.chocolateshop.models.Chocolate;
import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.models.OrderDetails;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.OrderDetailsRepository;
import com.example.chocolateshop.repositories.OrderRepository;
import com.example.chocolateshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{
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



    public List<Order> getOrderByUser(String name) {
        User user = userRepository.findByFullName(name);
        List<Order> orderList = orderRepository.findAllByUserId(user.getId());
        return orderList;
    }





}
