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

@RestController // @Controller 대신 @RestController 사용 (JSON 응답용)
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders") // 공통 경로 설정
public class OrderController {

    private final OrderService orderService;

    // 주문 생성 시 사용할 데이터 객체 (DTO)
    public record OrderCreateRequest(
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,

            @NotBlank(message = "주소는 필수입니다.")
            String address
    ) {}


    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderCreateRequest request) {
        Order order = orderService.create(
                request.name(),
                request.email(),
                request.address()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


    @GetMapping
    public ResponseEntity<List<Order>> list() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Order> detail(@PathVariable Integer id) { // ID 타입은 Long 권장
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }


    @PostMapping("/new")
    public ResponseEntity<Void> createOrder(@Valid @RequestBody OrderCreateRequest request) {
        orderService.create(request.name(), request.email(), request.address());
        return ResponseEntity.ok().build();
    }
}

