package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findAllByUserId(Long id, Pageable pageable);
    List<Order> findAllByUserId(Long id);
    Page<Order> findAll(Pageable pageable);

}
