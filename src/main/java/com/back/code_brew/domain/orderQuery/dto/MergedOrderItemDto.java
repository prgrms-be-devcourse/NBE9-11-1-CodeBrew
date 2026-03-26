package com.back.code_brew.domain.orderQuery.dto;

public record MergedOrderItemDto(
        Integer orderId,
        Integer productId,
        String productName,
        int quantity,
        int price
) {
}