package com.back.code_brew.domain.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String email;

    @Column(name = "total_price")
    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    private String address;

    @CreatedDate
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(String name, String email, Long totalPrice, String address) {
        this.name = name;
        this.email = email;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PENDING;
        this.address = address;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void clearOrderItems() {
        this.orderItems.clear();
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
    }

    public void updateOrderInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void updateTotalPrice() {
        this.totalPrice = (long) this.orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
