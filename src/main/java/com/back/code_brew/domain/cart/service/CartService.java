package com.back.code_brew.domain.cart.service;

import com.back.code_brew.domain.cart.dto.CartItemDto;
import com.back.code_brew.domain.cart.dto.CartItemQuantityDto;
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
    public CartItem changeQuantity(Integer id, CartItemQuantityDto cartItemQuantityDto){
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 장바구니 항목이 없습니다."));
        int quantity = cartItemQuantityDto.getQuantity();
        cartItem.changeQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }
    public void removeItem(){

    }
}