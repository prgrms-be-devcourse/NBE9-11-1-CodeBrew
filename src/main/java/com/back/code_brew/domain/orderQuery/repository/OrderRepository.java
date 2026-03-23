package com.back.code_brew.domain.orderQuery.repository;

import com.back.code_brew.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmailOrderByCreatedAtDesc(String email);
}