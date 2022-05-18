package com.example.chocolateshop.repositories;


import com.example.chocolateshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
