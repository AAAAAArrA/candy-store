package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Chocolate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChocolateRepository extends JpaRepository<Chocolate, Long> {
}
