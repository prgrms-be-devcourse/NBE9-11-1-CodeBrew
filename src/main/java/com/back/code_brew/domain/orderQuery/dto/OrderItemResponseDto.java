package com.back.code_brew.domain.orderQuery.dto;

import com.back.code_brew.domain.order.OrderItem;

// 주문 상세 안에 들어갈 상품 목록용
public record OrderItemResponseDto(
        Long productId,
        String productName,
        int quantity,
        int price
) {
    public OrderItemResponseDto(OrderItem orderItem) {
        this(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getProductName(),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}