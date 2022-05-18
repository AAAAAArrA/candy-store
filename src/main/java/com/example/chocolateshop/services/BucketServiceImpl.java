package com.example.chocolateshop.services;

import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.dto.BucketDetailsDTO;
import com.example.chocolateshop.enums.Status;
import com.example.chocolateshop.models.*;
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
    private final OrderService orderService;
    public BucketServiceImpl(BucketRepository bucketRepository, ChocolateRepository chocolateRepository, UserRepository userRepository, OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.chocolateRepository = chocolateRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
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

    @Override
    @Transactional
    public void commitBucketToOrder(String username) {
        User user = userRepository.findByFullName(username);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        Bucket bucket = user.getBucket();
        if(bucket==null || bucket.getChocolateList().isEmpty()){
            return;
        }
        Order order = new Order();
        order.setStatus(Status.NEW);
        order.setUser(user);
        Map<Chocolate, Long> productWithAmount = bucket.getChocolateList().stream()
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));

        List<OrderDetails> orderDetails = productWithAmount.entrySet().stream()
                .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue()))
                .collect(Collectors.toList());

        BigDecimal total = new BigDecimal(orderDetails.stream()
                .map(detail -> detail.getPrice().multiply(detail.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());
        order.setDetails(orderDetails);
        order.setSumm(total);
        order.setAddress("none");

        orderService.save(order);
        bucket.getChocolateList().clear();
        bucketRepository.save(bucket);
    }
}
