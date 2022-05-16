package com.example.chocolateshop.repositories;

import com.example.chocolateshop.models.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
