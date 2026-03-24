package com.back.code_brew.domain.orderQuery.service;

import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.entity.OrderItem;
import com.back.code_brew.domain.order.repository.OrderRepository;
import com.back.code_brew.domain.orderQuery.dto.*;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 이메일로 주문 목록 조회
    // 주문 목록
    public List<OrderListResponseDto> getOrdersByEmail(String email) {
        List<Order> orders = orderRepository.findByEmailOrderByCreatedAtDesc(email);

        return orders.stream()
                .map(OrderListResponseDto::new)
                .toList();
    }

    //관리자용 전체 주문 목록 (최신순)
    public List<AdminOrderListDto> getOrders() {
        List<Order> orders = orderRepository.findAllByOrderByCreatedAtDesc();

        return orders.stream()
                .map(AdminOrderListDto::new)
                .toList();
    }


    // 주문 상세 조회
    public OrderDetailResponseDto getOrderDetail(Long orderId) {
        Order order = orderRepository.findById(orderId.intValue())
                .orElseThrow(() -> new NoSuchElementException("해당 주문이 없습니다. orderId=" + orderId));

        return new OrderDetailResponseDto(order);
    }

    // 주문 취소
    @Transactional
    public OrderDetailResponseDto cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId.intValue())
                .orElseThrow(() -> new NoSuchElementException("해당 주문이 없습니다."));

        // 출고 전("ORDERED")일 때만 취소 가능
        if (!order.getStatus().equals("ORDERED")) {
            throw new IllegalArgumentException("출고 전 주문만 취소할 수 있습니다.");
        }

        order.cancel();

        return new OrderDetailResponseDto(order);
    }

    // 주문 수정
    @Transactional
    public OrderDetailResponseDto updateOrder(Long orderId, OrderUpdateRequestDto requestDto) {
        Order order = orderRepository.findById(orderId.intValue())
                .orElseThrow(() -> new NoSuchElementException("해당 주문이 없습니다."));

        if (!order.getStatus().equals("ORDERED")) {
            throw new IllegalArgumentException("출고 전 주문만 수정할 수 있습니다.");
        }

        order.updateOrderInfo(
                requestDto.customerName(),
                requestDto.address()
        );

        order.clearOrderItems();

        for (OrderUpdateItemRequestDto itemDto : requestDto.items()) {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new NoSuchElementException("존재하지 않는 상품입니다."));

            OrderItem orderItem = new OrderItem(
                    order,
                    product,
                    product.getPrice(),
                    itemDto.quantity()
            );

            order.addOrderItem(orderItem);
        }

        order.updateTotalPrice();

        return new OrderDetailResponseDto(order);
    }

    public Order updateStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        order.setStatus(status);

        return order;
    }
}