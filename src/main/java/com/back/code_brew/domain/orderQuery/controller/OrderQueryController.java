package com.back.code_brew.domain.orderQuery.controller;

import com.back.code_brew.domain.orderQuery.dto.MergedOrderResponseDto;
import com.back.code_brew.domain.orderQuery.dto.OrderDetailResponseDto;
import com.back.code_brew.domain.orderQuery.dto.OrderListResponseDto;
import com.back.code_brew.domain.orderQuery.dto.OrderUpdateRequestDto;
import com.back.code_brew.domain.orderQuery.service.OrderQueryService;
import com.back.code_brew.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-query")
@RequiredArgsConstructor
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    // 이메일로 주문 목록 조회
    // GET /api/v1/order-query?email=test@test.com
    @GetMapping
    public List<MergedOrderResponseDto> getOrdersByEmail(@RequestParam String email) {
        return orderQueryService.getMergedOrdersByEmail(email);
    }

    // 주문 상세 조회
    // GET /api/v1/order-query/{orderId}
    @GetMapping("/{orderId}")
    public OrderDetailResponseDto getOrderDetail(@PathVariable Long orderId) {
        return orderQueryService.getOrderDetail(orderId);
    }

    // 주문 취소
    // PATCH /api/v1/order-query/{orderId}/cancel
    @PatchMapping("/{orderId}/cancel")
    public RsData<OrderDetailResponseDto> cancelOrder(@PathVariable Long orderId) {
        OrderDetailResponseDto orderDetailResponseDto = orderQueryService.cancelOrder(orderId);

        return new RsData<>(
                "%d번 주문이 취소되었습니다.".formatted(orderId),
                "200-1",
                orderDetailResponseDto
        );
    }

    // 주문 수정
    // PUT /api/v1/order-query/{orderId}
    @PutMapping("/{orderId}")
    public RsData<OrderDetailResponseDto> updateOrder(
            @PathVariable Long orderId,
            @RequestBody @Valid OrderUpdateRequestDto requestDto
    ) {
        OrderDetailResponseDto orderDetailResponseDto = orderQueryService.updateOrder(orderId, requestDto);

        return new RsData<>(
                "%d번 주문이 수정되었습니다.".formatted(orderId),
                "200-1",
                orderDetailResponseDto
        );
    }
}
