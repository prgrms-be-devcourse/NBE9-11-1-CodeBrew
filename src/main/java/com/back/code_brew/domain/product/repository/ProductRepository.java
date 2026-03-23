package com.back.code_brew.domain.product.repository;

import com.back.code_brew.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}