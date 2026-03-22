package com.back.code_brew.domain.cart.service;

import com.back.code_brew.domain.cart.dto.CartItemDto;
import com.back.code_brew.domain.cart.dto.CartItemQuantityDto;
import com.back.code_brew.domain.cart.entity.CartItem;
import com.back.code_brew.domain.cart.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    public List<CartItem> getCart(){
        return cartItemRepository.findAll();
    }
    public CartItem addItem(CartItemDto cartItemDto){

        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductId(cartItemDto.getProductId());

        // 이미 존재하면 → 수량 증가
        if (optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();
            cartItem.addQuantity(cartItemDto.getQuantity());
            return cartItemRepository.save(cartItem);
        }

        // 없으면 → 새로 생성
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
    public void removeItem(Integer id){
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 장바구니 항목이 없습니다."));
        cartItemRepository.delete(cartItem);
    }
    public int getTotalPrice(){
        List<CartItem> cartItems = cartItemRepository.findAll();

        int totalPrice = 0;

        for(CartItem cartItem : cartItems){
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }
}