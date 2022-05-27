package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findAllByUserId(Long id, Pageable pageable);
    List<Order> findAllByUserId(Long id);
    Page<Order> findAll(Pageable pageable);
    @Query(value = "select * from 'order' where created between ? and ?", nativeQuery = true)
    List<Order> findAll(Date startDate, Date endDate);

}
