package com.back.code_brew.domain.orderQuery.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

// 주문 수정 요청용
public record OrderUpdateRequestDto(
        @NotBlank
        String customerName,

        @NotBlank
        String address,

        @NotEmpty
        @Valid
        List<OrderUpdateItemRequestDto> items
) {
}