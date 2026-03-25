package com.back.code_brew.domain.orderQuery.controller;

import com.back.code_brew.domain.orderQuery.dto.AdminOrderListDto;
import com.back.code_brew.domain.orderQuery.dto.AdminOrderStatusDto;
import com.back.code_brew.domain.orderQuery.service.OrderQueryService;
import com.back.code_brew.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/orders")
public class AdminOrderQueryController {

    private final OrderQueryService orderQueryService;

    @GetMapping
    public List<AdminOrderListDto> getOrders() {
        return orderQueryService.getOrders();
    }

    @PatchMapping("/{orderId}")
    public RsData<AdminOrderListDto> updateStatus(
            @PathVariable Integer orderId,
            @RequestBody AdminOrderStatusDto requestDto
    ) {
        AdminOrderListDto response = orderQueryService.updateStatus(orderId, requestDto);

        return new RsData<>(
                "주문 묶음 상태가 변경되었습니다.",
                "200-1",
                response
        );

    }


}
