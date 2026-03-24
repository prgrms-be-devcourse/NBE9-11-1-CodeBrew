package com.back.code_brew.domain.orderQuery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// 주문 수량 수정
public record OrderUpdateItemRequestDto(
        @NotNull
        int productId,

        @Min(1)
        int quantity
) {
}