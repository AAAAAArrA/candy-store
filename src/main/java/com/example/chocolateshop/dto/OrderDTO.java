package com.example.chocolateshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
