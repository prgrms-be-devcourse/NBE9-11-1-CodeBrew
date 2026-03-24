package com.back.code_brew.domain.order.service;

import com.back.code_brew.domain.order.dto.OrderItemRequest;
import com.back.code_brew.domain.order.dto.OrderRequest;
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

    public Order create(String name, String email, String address) {
        Order order = new Order(
                name,
                email,
                0L, // 초기값
                "배송 준비 중",
                address
        );
        return orderRepository.save(order);
    }

    @Transactional
    public Order createOrder(OrderRequest request) {

        Order order = create(
                request.getName(),
                request.getEmail(),
                request.getAddress()
        );

        long totalPrice = 0;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());
            order.addOrderItem(orderItem);
            totalPrice += product.getPrice() * itemRequest.getQuantity();

            orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(totalPrice);

        return order;
    }

    public Order findById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주문 없음"));
    }

    public List<Order> findAll() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("주문 없음");
        }
        return orders;
    }
}
