package com.back.code_brew.domain.cart.service;

import com.back.code_brew.domain.cart.dto.CartItemDto;
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
    public CartItem addItem(CartItemDto cartItemDto){
        CartItem cartItem = new CartItem(
                cartItemDto.getProductId(),
                cartItemDto.getProductName(),
                cartItemDto.getPrice(),
                cartItemDto.getQuantity()
        );
        return cartItemRepository.save(cartItem);
    }
    public void removeItem(){

    }
    public void changeQuantity(){

    }
}