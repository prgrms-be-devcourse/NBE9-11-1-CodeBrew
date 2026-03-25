package com.back.code_brew.domain.orderQuery.dto;

public record MergedOrderItemDto(
        Integer productId,
        String productName,
        int quantity,
        int price
) {
}