package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Bucket;
import com.example.chocolateshop.models.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);
    void addProduct(Bucket  bucket, List<Long> productIds);

}
