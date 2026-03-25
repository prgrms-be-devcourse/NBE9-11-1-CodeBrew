package com.back.code_brew.domain.orderQuery.dto;

import java.time.LocalDate;
import java.util.List;

public record MergedOrderResponseDto(
        String email,
        LocalDate orderDate,
        Long totalPrice,
        List<MergedOrderItemDto> items
) {
}