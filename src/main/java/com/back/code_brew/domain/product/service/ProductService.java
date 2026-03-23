package com.back.code_brew.domain.product.service;

import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product create(String productName, int price) {
        Product product = new Product(productName, price);
        return productRepository.save(product);
    }

    @Transactional
    public Product modify(int id, String productName, int price) {
        Product product = findById(id);
        product.update(productName, price);
        return product;
    }

    @Transactional
    public void deleteById(int id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(()-> new NoSuchElementException("해당하는 상품이 없습니다."));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public long count() {
        return productRepository.count();
    }
}
