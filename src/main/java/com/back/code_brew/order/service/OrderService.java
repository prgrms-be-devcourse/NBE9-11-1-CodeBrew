package com.back.code_brew.order.service;

import com.back.code_brew.order.entity.Order;
import com.back.code_brew.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order create(String name, String email, String address) {
        Order order = new Order(
                name,
                email,
                0L, // 초기값
                "배송 준비 중",
                address
        );
        return orderRepository.save(order);
    }
}
