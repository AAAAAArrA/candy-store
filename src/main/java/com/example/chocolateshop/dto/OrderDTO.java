package com.example.chocolateshop.dto;

import com.example.chocolateshop.models.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    private int amountProducts;
    private LocalDateTime created;
    private Double summ;
    private String address;
    private List<OrderDetailsDTO> details = new ArrayList<>();

}
