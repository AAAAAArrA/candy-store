package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.dto.BucketDetailsDTO;
import com.example.chocolateshop.models.Bucket;
import com.example.chocolateshop.models.Chocolate;
import com.example.chocolateshop.models.User;
import com.example.chocolateshop.repositories.BucketRepository;
import com.example.chocolateshop.repositories.ChocolateRepository;
import com.example.chocolateshop.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{
    private final BucketRepository bucketRepository;
    private final ChocolateRepository chocolateRepository;
    private final UserRepository userRepository;
    public BucketServiceImpl(BucketRepository bucketRepository, ChocolateRepository chocolateRepository, UserRepository userRepository) {
        this.bucketRepository = bucketRepository;
        this.chocolateRepository = chocolateRepository;
        this.userRepository = userRepository;

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

    @Override
    public BucketDTO getBucketByUser(String name) {
        User user = userRepository.findByFullName(name);
        if (user == null || user.getBucket() == null){
            return new BucketDTO();
        }
        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailsDTO> mapByProductId = new HashMap<>();
        List<Chocolate> chocolates = user.getBucket().getChocolateList();
        for(Chocolate product : chocolates){
            BucketDetailsDTO details = mapByProductId.get(product.getId());
            if(details == null){
                mapByProductId.put(product.getId(), new BucketDetailsDTO(product));
            }else {
                details.setAmount(details.getAmount().add(new BigDecimal(1.0)));
                details.setSum(details.getSum() + Double.valueOf(product.getPrice().toString()));
            }
        }
        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();
        return bucketDTO;
    }
}
