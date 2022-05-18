package com.example.chocolateshop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Chocolate chocolate;
    private BigDecimal amount;
    private BigDecimal price;

    public OrderDetails(Order order, Chocolate chocolate, Long amount) {
        this.order = order;
        this.chocolate = chocolate;
        this.amount = new BigDecimal(amount);
        this.price = new BigDecimal(String.valueOf(chocolate.getPrice()));
    }
}
