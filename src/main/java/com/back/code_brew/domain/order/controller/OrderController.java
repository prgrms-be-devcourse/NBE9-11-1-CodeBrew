package com.back.code_brew.domain.order.controller;

import com.back.code_brew.domain.order.dto.OrderRequest;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public record OrderCreateRequest(
            @NotBlank(message = "01-name-이름은 필수입니다.")
            String name,

            @Email(message = "02-email-올바른 이메일 형식이어야 합니다.")
            @NotBlank(message = "03-email-이메일은 필수입니다.")
            String email,

            @NotBlank(message = "04-address-주소는 필수입니다.")
            String address
    ){}


    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderCreateRequest request) {
        Order order = orderService.create(
                request.name(),
                request.email(),
                request.address()
        );
        // 성공 시 201 Created 상태 코드와 생성된 주문 객체 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> list() {
        List<Order> orders = orderService.findAll(); // 서비스에 findAll()이 있다고 가정
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> detail(@PathVariable Integer id) { // int -> Long 권장
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/new")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderRequest request) {
        orderService.createOrder(request);
        return ResponseEntity.ok().build();
    }
}

