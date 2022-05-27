package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
    List<Bucket> findAll();
    void deleteAllProductsById(Long id);
}
