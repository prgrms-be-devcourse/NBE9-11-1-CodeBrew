package com.back.code_brew.domain.order.controller;

import com.back.code_brew.domain.order.dto.OrderRequest;
import com.back.code_brew.domain.order.dto.OrderResponse;
import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 전체 주문 목록 조회
    @GetMapping
    public ResponseEntity<List<OrderResponse>> list() {
        return ResponseEntity.ok(orderService.findAll());
    }

    // 주문 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> detail(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
}

