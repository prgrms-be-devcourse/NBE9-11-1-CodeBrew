package com.back.code_brew.domain.order.service;

import com.back.code_brew.domain.order.dto.OrderItemRequest;
import com.back.code_brew.domain.order.dto.OrderRequest;
import com.back.code_brew.domain.order.dto.OrderResponse;
import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.entity.OrderItem;
import com.back.code_brew.domain.order.repository.OrderItemRepository;
import com.back.code_brew.domain.order.repository.OrderRepository;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        Order order = new Order(
                request.getName(),
                request.getEmail(),
                0L,
                "배송 준비 중",
                request.getAddress()
        );

        long totalPrice = 0;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. ID: " + itemRequest.getProductId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());

            order.addOrderItem(orderItem);

            totalPrice += (long) product.getPrice() * itemRequest.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.from(order);
    }

    public OrderResponse findById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문 없음"));

        return OrderResponse.from(order);
    }

    public List<OrderResponse> findAll() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderResponse::from)
                .toList();
    }
}
