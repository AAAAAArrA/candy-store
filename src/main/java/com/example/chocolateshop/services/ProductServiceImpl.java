package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.ProductDTO;
import com.example.chocolateshop.mapper.ProductMapper;
import com.example.chocolateshop.models.Bucket;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.BucketRepository;
import com.example.chocolateshop.repositories.ChocolateRepository;
import com.example.chocolateshop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;



@Service
public class ProductServiceImpl implements ProductService{
    private final ProductMapper productMapper = ProductMapper.MAPPER;

    private final ChocolateRepository chocolateRepository;
    private final UserServiceImpl userService;
    private final BucketService bucketService;

    public ProductServiceImpl(ChocolateRepository chocolateRepository, UserRepository userRepository, BucketRepository bucketRepository, UserServiceImpl userService, BucketService bucketService) {
        this.chocolateRepository = chocolateRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDTO> getAll() {
        return null;
    }

    @Override
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findUser(username);
        if(user == null){
            throw new RuntimeException("User " + username + " not found");
        }
        Bucket bucket = user.getBucket();
        if(bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        }else{
            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }


    }
}
