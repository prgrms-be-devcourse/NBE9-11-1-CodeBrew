package com.back.code_brew.order.repository;

import com.back.code_brew.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
