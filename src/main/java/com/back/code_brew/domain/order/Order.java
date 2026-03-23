package com.back.code_brew.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int totalPrice;

    private String address;

    private String status;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(String name, String email, String address, String status) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.status = status;
    }

    public void cancel() {
        this.status = "CANCELED";
    }

    public void updateOrderInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void clearOrderItems() {
        this.orderItems.clear();
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void updateTotalPrice() {
        this.totalPrice = this.orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}