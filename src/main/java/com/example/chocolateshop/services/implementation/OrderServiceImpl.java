package com.example.chocolateshop.services.implementation;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.OrderDetailsRepository;
import com.example.chocolateshop.repositories.OrderRepository;
import com.example.chocolateshop.repositories.UserRepository;
import com.example.chocolateshop.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getPaginatedOrdersBuUser(String name, int pageNo, int pageSize) {
        User user = userRepository.findByFullName(name);
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return orderRepository.findAllByUserId(user.getId(), pageable);
    }
    @Override
    public Page<Order> getAllPaginatedOrders(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return  orderRepository.findAll(pageable);
    }




}
