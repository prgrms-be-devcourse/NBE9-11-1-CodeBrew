package com.back.code_brew.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String order_id;
    private String product_id;
    private int price;
    private int quantity;

    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
