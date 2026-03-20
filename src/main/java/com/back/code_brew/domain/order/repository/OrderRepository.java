package com.back.code_brew.domain.order.repository;

import com.back.code_brew.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
