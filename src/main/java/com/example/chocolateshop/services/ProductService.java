package com.example.chocolateshop.services;


import com.example.chocolateshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
    void addToUserBucket(Long productId, String username);
}
