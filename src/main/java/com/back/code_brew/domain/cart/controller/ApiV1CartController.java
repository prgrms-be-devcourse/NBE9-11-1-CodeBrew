package com.back.code_brew.domain.cart.controller;

import com.back.code_brew.domain.cart.entity.CartItem;
import com.back.code_brew.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
