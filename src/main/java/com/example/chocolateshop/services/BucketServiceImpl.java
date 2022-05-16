package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Bucket;
import com.example.chocolateshop.models.Chocolate;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.BucketRepository;
import com.example.chocolateshop.repositories.ChocolateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ChocolateRepository chocolateRepository;
    public BucketServiceImpl(BucketRepository bucketRepository, ChocolateRepository chocolateRepository) {
        this.bucketRepository = bucketRepository;
        this.chocolateRepository = chocolateRepository;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Chocolate> chocolateList = getCollectRefProductsById(productIds);
        bucket.setChocolateList(chocolateList);
        return bucketRepository.save(bucket);
    }

    private List<Chocolate> getCollectRefProductsById(List<Long> productIds) {
        return productIds.stream()
                //getOne вытаскивает ссылку на объект, а findById - сам объект
                .map(chocolateRepository::getOne)
                .collect(Collectors.toList());
    }



    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Chocolate> products = bucket.getChocolateList();
        List<Chocolate> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsById(productIds));
        bucket.setChocolateList(newProductList);
        bucketRepository.save(bucket);


    }
}
