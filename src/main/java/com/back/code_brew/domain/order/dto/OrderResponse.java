package com.back.code_brew.domain.order.dto;

import com.back.code_brew.domain.order.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
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
                order.getStatus().name(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(OrderItemResponse::from)
                        .toList()
        );
    }
}