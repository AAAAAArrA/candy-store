package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Order;
import com.example.chocolateshop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserId(Long id);

}
