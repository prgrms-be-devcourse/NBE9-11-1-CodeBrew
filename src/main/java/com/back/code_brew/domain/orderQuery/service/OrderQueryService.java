package com.back.code_brew.domain.orderQuery.service;

import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.entity.OrderItem;
import com.back.code_brew.domain.order.entity.OrderStatus;
import com.back.code_brew.domain.order.repository.OrderRepository;
import com.back.code_brew.domain.orderQuery.dto.*;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 이메일 입력을 통한 날짜별로 통합된 주문 목록 조회, 주문 날짜 기준이 아닌 14시 기준으로 계산
    public List<MergedOrderResponseDto> getMergedOrdersByEmail(String email) {
        List<Order> orders = orderRepository.findByEmailOrderByCreatedAtDesc(email);

        return orders.stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        order -> getBatchDate(order.getCreatedAt()),
                        java.util.LinkedHashMap::new,
                        java.util.stream.Collectors.toList()
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    java.time.LocalDate orderDate = entry.getKey();
                    List<Order> dailyOrders = entry.getValue();

                    java.util.Map<Integer, MergedOrderItemDto> mergedItemMap = new java.util.LinkedHashMap<>();
                    long totalPrice = 0L;

                    for (Order order : dailyOrders) {
                        totalPrice += order.getTotalPrice();

                        for (OrderItem orderItem : order.getOrderItems()) {
                            Integer productId = orderItem.getProduct().getId();

                            if (mergedItemMap.containsKey(productId)) {
                                MergedOrderItemDto existingItem = mergedItemMap.get(productId);

                                mergedItemMap.put(productId, new MergedOrderItemDto(
                                        existingItem.productId(),
                                        existingItem.productName(),
                                        existingItem.quantity() + orderItem.getQuantity(),
                                        existingItem.price()
                                ));
                            } else {
                                mergedItemMap.put(productId, new MergedOrderItemDto(
                                        orderItem.getProduct().getId(),
                                        orderItem.getProduct().getProductName(),
                                        orderItem.getQuantity(),
                                        orderItem.getPrice()
                                ));
                            }
                        }
                    }

                    return new MergedOrderResponseDto(
                            email,
                            orderDate,
                            totalPrice,
                            new java.util.ArrayList<>(mergedItemMap.values())
                    );
                })
                .toList();
    }


    // 관리자용 전체 주문 목록 (최신순)
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

        if (order.getStatus() != OrderStatus.PENDING) {
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

        if (order.getStatus() != OrderStatus.PENDING) {
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

    // 관리자 출고 처리
    @Transactional
    public AdminOrderListDto updateStatus(Integer orderId, AdminOrderStatusDto requestDto) {
        Order targetOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));

        String email = targetOrder.getEmail();
        LocalDate batchDate = getBatchDate(targetOrder.getCreatedAt());

        List<Order> orders = orderRepository.findByEmailOrderByCreatedAtDesc(email);

        List<Order> targetOrders = orders.stream()
                .filter(order -> getBatchDate(order.getCreatedAt()).equals(batchDate))
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .toList();

        for (Order order : targetOrders) {
            order.changeStatus(requestDto.status());
        }

        return new AdminOrderListDto(targetOrder);
    }

    private LocalDate getBatchDate(java.time.LocalDateTime createdAt) {
        if (createdAt.getHour() < 14) {
            return createdAt.toLocalDate().minusDays(1);
        }
        return createdAt.toLocalDate();
    }
}
