package com.back.code_brew.order.service;

import com.back.code_brew.domain.order.dto.OrderItemRequest;
import com.back.code_brew.domain.order.dto.OrderRequest;
import com.back.code_brew.domain.order.entity.Order;
import com.back.code_brew.domain.order.repository.OrderRepository;
import com.back.code_brew.domain.order.service.OrderService;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    @DisplayName("주문 생성 성공")
    void test1(){
        Product product = new Product();
        product.setProductName("test");
        product.setPrice(5500);
        productRepository.save(product);

        OrderItemRequest item = new OrderItemRequest();
        item.setProductId(product.getId());
        item.setQuantity(2);

        OrderRequest request = new OrderRequest();
        request.setName("홍길동");
        request.setEmail("test@test.com");
        request.setAddress("서울");
        request.setItems(List.of(item));

        Order order = orderService.createOrder(request);

        assertThat(order).isNotNull();
        assertThat(order.getId()).isNotNull();

        Order saved = orderRepository.findById(order.getId()).orElseThrow();

        assertThat(saved.getTotalPrice()).isEqualTo(11000); // 1000 * 2
        assertThat(saved.getOrderItems().size()).isEqualTo(1);
    }
}
