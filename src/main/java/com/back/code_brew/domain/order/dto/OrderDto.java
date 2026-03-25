package com.back.code_brew.domain.order.dto;

import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

/*
record OrderResponse(
        int id,
        String name,
        String email,
        String address,
        String status,
        Long totalPrice,
        LocalDateTime createdAt,
        List<OrderItemResponse> orderItems
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getName(),
                order.getEmail(),
                order.getAddress(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(OrderItemResponse::from)
                        .toList()
        );
    }
}

record OrderItemResponse(
        int productId,
        String productName,
        int quantity,
        int price
) {
    public static OrderItemResponse from(OrderItem item) {
        return new OrderItemResponse(
                item.getProduct().getId(),
                item.getProduct().getProductName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
}

 */