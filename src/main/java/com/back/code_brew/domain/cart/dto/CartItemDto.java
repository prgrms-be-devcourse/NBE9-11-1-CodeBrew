package com.back.code_brew.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
   private Long productId;
   private String productName;
   private int price;
   private int quantity;
}
