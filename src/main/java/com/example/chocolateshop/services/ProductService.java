package com.example.chocolateshop.services;


import com.example.chocolateshop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ProductService {
    Page<Product> getAll(Pageable pageable);
    void addToUserBucket(Long productId, String username);
    Product saveProductToDB(Product product, MultipartFile multipartFile)throws IOException;
    void deleteProduct(Long id);
    Product findProduct(Long id);
    Page<Product> findPaginated(int pageNo, int pageSize);

}
