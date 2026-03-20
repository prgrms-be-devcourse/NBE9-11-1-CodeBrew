package com.back.code_brew.domain.cart.service;

import com.back.code_brew.domain.cart.entity.CartItem;
import com.back.code_brew.domain.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;

    public List<CartItem> getCart(){
        return cartItemRepository.findAll();
    }
    public void addItem(){

    }
    public void removeItem(){

    }
    public void changeQuantity(){

    }
}