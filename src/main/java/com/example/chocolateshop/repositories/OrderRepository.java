package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserId(Long id);

}
