package com.back.code_brew.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String name;
    private String email;
    private String address;

    private List<OrderItemRequest> items;
}
