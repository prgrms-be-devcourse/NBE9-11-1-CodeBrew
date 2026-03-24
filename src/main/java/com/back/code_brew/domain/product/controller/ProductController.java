package com.back.code_brew.domain.product.controller;

import com.back.code_brew.domain.product.dto.ProductDto;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    //상품 다건조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> list() {
        List<Product> result = productService.findAll();

        List<ProductDto> productDtoList = result.reversed().stream()
                .map(ProductDto::new)
                .toList();

        return productDtoList;
    }

    //상품 단건조회
    @GetMapping("/{id}")
    public ProductDto detail(@PathVariable int id) {
        Product product = productService.findById(id);
        return new ProductDto(product);
    }





}
