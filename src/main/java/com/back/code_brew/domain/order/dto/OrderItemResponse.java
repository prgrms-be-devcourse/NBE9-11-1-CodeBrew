package com.back.code_brew.domain.order.dto;

import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public record OrderItemResponse(
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
