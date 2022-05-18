package com.example.chocolateshop.services;


import com.example.chocolateshop.dto.ProductDTO;
import com.example.chocolateshop.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    List<Product> getAll();
    void addToUserBucket(Long productId, String username);
    Product saveProductToDB(Product product, MultipartFile multipartFile)throws IOException;
    void deleteProduct(Long id);
    Product findProduct(Long id);
}
