package com.example.chocolateshop.services.implementation;

import com.example.chocolateshop.models.OrderDetails;
import com.example.chocolateshop.repositories.OrderDetailsRepository;
import com.example.chocolateshop.services.OrderDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsServiceImpl(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    public List<OrderDetails> getDetails(){
        return orderDetailsRepository.findAll();
    }



}
