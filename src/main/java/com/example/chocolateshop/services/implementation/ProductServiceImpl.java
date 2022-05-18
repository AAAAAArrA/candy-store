package com.example.chocolateshop.services.implementation;

import com.example.chocolateshop.mapper.ProductMapper;
import com.example.chocolateshop.models.Bucket;
import com.example.chocolateshop.models.Product;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.ProductRepository;
import com.example.chocolateshop.services.BucketService;
import com.example.chocolateshop.services.CustomUserService;
import com.example.chocolateshop.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;



@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final CustomUserService customUserService;
    private final BucketService bucketService;

    public ProductServiceImpl(ProductRepository productRepository, CustomUserService customUserService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.customUserService = customUserService;
        this.bucketService = bucketService;
    }
    @Override
    public Product saveProductToDB(Product product, MultipartFile multipartFile)throws IOException{
        product.setImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public Product findProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void addToUserBucket(Long productId, String username) {
        User user = customUserService.findUser(username);
        if(user == null){
            throw new RuntimeException("User " + username + " not found");
        }
        Bucket bucket = user.getBucket();
        if(bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            customUserService.save(user);
        }else{
            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }
    }
}
