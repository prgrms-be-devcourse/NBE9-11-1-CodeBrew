package com.back.code_brew.global.initData;

import com.back.code_brew.domain.order.Order;
import com.back.code_brew.domain.order.OrderItem;
import com.back.code_brew.domain.orderQuery.repository.OrderRepository;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    @Autowired
    @Lazy
    private BaseInitData self;

    // OrderService 부재로 Repository 사용
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Bean
    public ApplicationRunner initData() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if (productRepository.count() > 0 || orderRepository.count() > 0) {
            return;
        }

        Product product1 = new Product(
                "Guatemala Erwin Bourbon&Typica",
                27500
        );

        Product product2 = new Product(
                "Rwanda Mahembe Willy Bourbon Anaerobic",
                26500
        );

        Product product3 = new Product(
                "Bolivia Caranavi Jorge Java Washed",
                26500
        );

        Product product4 = new Product(
                "Mexico Patlanalán",
                26500
        );

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        Order order1 = new Order(
                "김철수",
                "test@example.com",
                "서울시 마포구 월드컵북로 10",
                "ORDERED"
        );

        OrderItem order1Item1 = new OrderItem(order1, product1, product1.getPrice(), 1);
        OrderItem order1Item2 = new OrderItem(order1, product2, product2.getPrice(), 2);

        order1.addOrderItem(order1Item1);
        order1.addOrderItem(order1Item2);
        order1.updateTotalPrice();

        Order order2 = new Order(
                "이영희",
                "test@example.com",
                "서울시 강남구 테헤란로 20",
                "ORDERED"
        );

        OrderItem order2Item1 = new OrderItem(order2, product3, product3.getPrice(), 1);
        order2.addOrderItem(order2Item1);
        order2.updateTotalPrice();

        Order order3 = new Order(
                "박민수",
                "other@example.com",
                "서울시 송파구 올림픽로 30",
                "CANCELED"
        );

        OrderItem order3Item1 = new OrderItem(order3, product4, product4.getPrice(), 1);
        order3.addOrderItem(order3Item1);
        order3.updateTotalPrice();

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }


}
