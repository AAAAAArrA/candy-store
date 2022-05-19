package com.example.chocolateshop.services.implementation;

import com.example.chocolateshop.dto.BucketDTO;
import com.example.chocolateshop.dto.BucketDetailsDTO;
import com.example.chocolateshop.enums.Status;
import com.example.chocolateshop.models.*;
import com.example.chocolateshop.repositories.BucketRepository;
import com.example.chocolateshop.repositories.ProductRepository;
import com.example.chocolateshop.repositories.UserRepository;
import com.example.chocolateshop.services.BucketService;
import com.example.chocolateshop.services.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;
    public BucketServiceImpl(ProductRepository productRepository, BucketRepository bucketRepository, UserRepository userRepository, OrderService orderService) {
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> chocolateList = getCollectRefProductsById(productIds);
        bucket.setProductList(chocolateList);
        return bucketRepository.save(bucket);
    }
    @Override
    public List<Product> getCollectRefProductsById(List<Long> productIds) {
        return productIds.stream()
                //getOne вытаскивает ссылку на объект, а findById - сам объект
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }
    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProductList();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsById(productIds));
        bucket.setProductList(newProductList);
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
        List<Product> chocolates = user.getBucket().getProductList();
        for(Product product : chocolates){
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
        if(bucket==null || bucket.getProductList().isEmpty()){
            return;
        }
        Order order = new Order();
        order.setStatus(Status.NEW);
        order.setUser(user);
        Map<Product, Long> productWithAmount = bucket.getProductList().stream()
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
        bucket.getProductList().clear();
        bucketRepository.save(bucket);
    }

    @Override
    public List<Bucket> getAll() {
        return bucketRepository.findAll();
    }
}
