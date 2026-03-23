package com.back.code_brew.domain.order;

import com.back.code_brew.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private Integer price;

    private Integer quantity;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    public OrderItem(Order order, Product product, Integer price, Integer quantity) {
        this.order = order;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }
}
