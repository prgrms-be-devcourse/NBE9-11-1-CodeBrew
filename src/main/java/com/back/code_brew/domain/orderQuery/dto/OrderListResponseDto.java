package com.back.code_brew.domain.orderQuery.dto;

import com.back.code_brew.domain.order.entity.Order;

import java.time.LocalDateTime;

// 이메일로 주문 목록 조회용
public record OrderListResponseDto(
        Integer  orderId,
        String customerName,
        String email,
        String status,
        Long totalPrice,
        LocalDateTime createdAt
) {
    public OrderListResponseDto(Order order) {
        this(
                order.getId(),
                order.getName(),
                order.getEmail(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getCreatedAt()
        );
    }
}