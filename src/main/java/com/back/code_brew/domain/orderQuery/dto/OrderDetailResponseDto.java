package com.back.code_brew.domain.orderQuery.dto;

import com.back.code_brew.domain.order.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

// 주문 상세 조회용
public record OrderDetailResponseDto(
        int orderId,
        String customerName,
        String email,
        String address,
        String status,
        Long totalPrice,
        LocalDateTime createdAt,
        List<OrderItemResponseDto> items
) {
    public OrderDetailResponseDto(Order order) {
        this(
                order.getId(),
                order.getName(),
                order.getEmail(),
                order.getAddress(),
                order.getStatus().name(),
                order.getTotalPrice(),
                order.getCreatedAt(),
                order.getOrderItems().stream()
                        .map(OrderItemResponseDto::new)
                        .toList()
        );
    }
}