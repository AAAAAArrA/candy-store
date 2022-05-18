package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
