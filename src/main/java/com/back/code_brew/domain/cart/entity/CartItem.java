package com.back.code_brew.domain.cart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private int price;
    private int quantity;

    public CartItem(Long productId, String productName, int price, int quantity){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public void changeQuantity(int quantity){
      this.quantity = quantity;
    }
    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
    public int getTotalPrice(){
        return price * quantity;
    }
}
