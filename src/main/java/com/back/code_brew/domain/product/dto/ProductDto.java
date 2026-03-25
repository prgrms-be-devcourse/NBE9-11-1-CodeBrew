package com.back.code_brew.domain.product.dto;

import com.back.code_brew.domain.product.entity.Product;

public record ProductDto(
        int id,
        String productName,
        int price
) {
    public ProductDto(Product product) {
        this(
                product.getId(),
                product.getProductName(),
                product.getPrice()
        );
    }
}
