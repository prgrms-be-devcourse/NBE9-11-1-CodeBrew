package com.back.code_brew.domain.cart.repository;

import com.back.code_brew.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByProductId(Integer productId);
}
