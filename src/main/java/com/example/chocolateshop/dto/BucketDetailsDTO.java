package com.example.chocolateshop.dto;


import com.example.chocolateshop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailsDTO {
    private String title;
    private Long productId;
    private BigDecimal price;
    private BigDecimal amount;
    private Double sum;

    public BucketDetailsDTO(Product chocolate) {
        this.title = chocolate.getName();
        this.productId = chocolate.getId();
        this.price = chocolate.getPrice();
        this.amount = new BigDecimal(1.0);
        this.sum = Double.valueOf(chocolate.getPrice().toString());
    }
}
