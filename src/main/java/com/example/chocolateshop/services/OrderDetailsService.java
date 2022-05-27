package com.example.chocolateshop.services;

import com.example.chocolateshop.models.OrderDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailsService {
    List<OrderDetails> getDetails();
}
