package com.back.code_brew.domain.cart.controller;

import com.back.code_brew.domain.cart.dto.CartItemDto;
import com.back.code_brew.domain.cart.dto.CartItemQuantityDto;
import com.back.code_brew.domain.cart.entity.CartItem;
import com.back.code_brew.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class ApiV1CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartItem> getCart(){
        return cartService.getCart();
    }

    @PostMapping("/items")
    public CartItem addItem(@RequestBody CartItemDto cartItemDto){
        return cartService.addItem(cartItemDto);
    }

    @PatchMapping("/items/{id}")
    public CartItem changeQuantity(
            @PathVariable Integer id,
            @RequestBody CartItemQuantityDto cartItemQuantityDto
            ){
        return cartService.changeQuantity(id, cartItemQuantityDto);
    }
}
