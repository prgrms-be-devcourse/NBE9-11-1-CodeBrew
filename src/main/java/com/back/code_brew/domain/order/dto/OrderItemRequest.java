package com.back.code_brew.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private int productId;
    private int quantity;
}
