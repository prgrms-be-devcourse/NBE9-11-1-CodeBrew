package com.back.code_brew.domain.product.controller;

import com.back.code_brew.domain.product.dto.ProductDto;
import com.back.code_brew.domain.product.entity.Product;
import com.back.code_brew.domain.product.service.ProductService;
import com.back.code_brew.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/products")
public class AdminProductController {

    private final ProductService productService;

    record ProductCreateReqBody(
            @Size(max = 30, message = "상품명은 30자 이내로 입력해주세요.")
            @NotBlank(message = "상품명을 입력해주세요.")
            String productName,

            @NotNull
            @Min(value = 100, message = "가격은 100원 이상입니다.")
            int price
    ) {
    }

    record ProductCreateResBody(
            ProductDto productDto,
            long productsCount
    ) {
    }

    //상품 등록
    @PostMapping
    public RsData<ProductCreateResBody> create(@RequestBody @Valid ProductCreateReqBody reqBody) {
        Product product = productService.create(reqBody.productName, reqBody.price);
        long productsCount = productService.count();

        return new RsData<>(
                "%d번 상품이 추가되었습니다.".formatted(product.getId()),
                "201-1",
                new ProductCreateResBody(
                        new ProductDto(product),
                        productsCount
                )
        );
    }


    record ProductModifyReqBody(
            @Size(max = 30, message = "상품명은 30자 이내로 입력해주세요.")
            @NotBlank(message = "상품명 입력을 입력해주세요.")
            String productName,

            @NotNull
            @Min(value = 100, message = "가격은 100원 이상입니다.")
            int price
    ) {
    }

    record ProductModifyResBody(
            ProductDto productDto
    ) {
    }

    //상품 수정
    @PutMapping("/{id}")
    public RsData<ProductModifyResBody> modify(@PathVariable int id, @RequestBody @Valid ProductModifyReqBody reqBody) {
        Product product = productService.modify(id, reqBody.productName, reqBody.price);

        return new RsData<>(
                "%d번 상품이 수정되었습니다.".formatted(product.getId()),
                "200-1",
                new ProductModifyResBody(
                        new ProductDto(product)
                )
        );
    }

    //상품 삭제
    @DeleteMapping("/{id}")
    public RsData<Void> delete(@PathVariable int id) {
        productService.deleteById(id);

        return new RsData<>(
                "%d번 상품이 삭제되었습니다.".formatted(id),
                "201-1"
        );
    }
}
