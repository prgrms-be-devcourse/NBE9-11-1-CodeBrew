package com.back.code_brew.order.dto;

import com.back.code_brew.order.entity.Order;

import java.time.LocalDateTime;

public record OrderDto (
    Long id,
    String name,
    String email,
    String address,
    LocalDateTime created_at
){
    public OrderDto(Order order){
        this(
                order.getId(),
                order.getName(),
                order.getEmail(),
                order.getAddress(),
                order.getCreated_at()
        );
    }
}
