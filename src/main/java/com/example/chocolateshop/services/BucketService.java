package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.models.Bucket;
import com.example.chocolateshop.models.Product;
import com.example.chocolateshop.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    List<Product> getCollectRefProductsById(List<Long> productIds);
    void addProduct(Bucket  bucket, List<Long> productIds);
    BucketDTO getBucketByUser(String name);
    void commitBucketToOrder(String name);
    List<Bucket> getAll();
}
