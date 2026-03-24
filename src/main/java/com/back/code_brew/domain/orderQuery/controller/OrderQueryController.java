package com.back.code_brew.domain.orderQuery.controller;

import com.back.code_brew.domain.orderQuery.dto.OrderDetailResponseDto;
import com.back.code_brew.domain.orderQuery.dto.OrderListResponseDto;
import com.back.code_brew.domain.orderQuery.dto.OrderUpdateRequestDto;
import com.back.code_brew.domain.orderQuery.service.OrderQueryService;
import com.back.code_brew.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    @GetMapping
    public List<OrderListResponseDto> getOrdersByEmail(@RequestParam String email) {
        return orderQueryService.getOrdersByEmail(email);
    }

    @GetMapping("/{orderId}")
    public OrderDetailResponseDto getOrderDetail(@PathVariable Long orderId) {
        return orderQueryService.getOrderDetail(orderId);
    }

    @PatchMapping("/{orderId}/cancel")
    public RsData<OrderDetailResponseDto> cancelOrder(@PathVariable Long orderId) {
        OrderDetailResponseDto orderDetailResponseDto = orderQueryService.cancelOrder(orderId);

        return new RsData<>(
                "%d번 주문이 취소되었습니다.".formatted(orderId),
                "200-1",
                orderDetailResponseDto
        );
    }

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
 */