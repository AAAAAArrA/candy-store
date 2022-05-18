package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.models.OrderDetails;
import com.example.chocolateshop.repositories.OrderDetailsRepository;
import com.example.chocolateshop.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderRepository = orderRepository;
    }
//    public List<OrderDetails> getDetails(Long id){
//        Order order = orderRepository.findById(id).get();
//        return orderDetailsRepository.findOrderDetailsByOrderId((long) order.getId());
//    }

//    public List<OrderDetails> getDetails(Long id) {
//        return orderDetailsRepository.findOrderDetailsByOrderId(id);
//    }

    public List<OrderDetails> details(){
        return orderDetailsRepository.findAll();
    }
}
