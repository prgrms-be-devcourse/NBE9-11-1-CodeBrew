package com.back.code_brew.domain.orderQuery.dto;

import com.back.code_brew.domain.order.entity.OrderStatus;

public record AdminOrderStatusDto(
        OrderStatus status
) {
}
