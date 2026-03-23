package com.back.code_brew.domain.orderQuery.dto;

import com.back.code_brew.domain.order.Order;

import java.time.LocalDateTime;
import java.util.List;

// 주문 상세 조회용
public record OrderDetailResponseDto(
        Long orderId,
        String customerName,
        String email,
        String address,
        String status,
        int totalPrice,
        LocalDateTime createdAt,
        List<OrderItemResponseDto> items
) {
    public OrderDetailResponseDto(Order order) {
        this(
                order.getId(),
                order.getName(),
                order.getEmail(),
                order.getAddress(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(OrderItemResponseDto::new)
                        .toList()
        );
    }
}