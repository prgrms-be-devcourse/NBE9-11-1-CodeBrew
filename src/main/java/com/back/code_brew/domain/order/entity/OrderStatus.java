package com.back.code_brew.domain.order.entity;

public enum OrderStatus {
    PENDING,   // 출고 전, 수정/취소 가능
    SHIPPED,   // 출고 완료, 수정/취소 불가
    CANCELED   // 주문 취소
}