package com.back.code_brew.global.initData;

import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {

    private final ProductService productService;

    @Bean
    public ApplicationRunner initData() {
        return args -> {
            initProducts();
            //initAdmin();
        };
    }

    public void initProducts() {
        if (productService.count() > 0) {
            return;
        }
        Product product1 = productService.create("Ethiopia Sidamo", 5000);
        Product product2 = productService.create("Columbia Quindio", 3000);
        Product product3 = productService.create("Brazil Serra Do Caparao", 7000);
        Product product4 = productService.create("Columbia Narino", 6500);

    }
}
