package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
