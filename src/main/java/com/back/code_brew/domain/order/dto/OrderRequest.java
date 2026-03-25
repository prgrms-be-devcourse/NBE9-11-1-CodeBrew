package com.back.code_brew.domain.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotBlank(message = "이름은 필수입니다.")
    private String name;
    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
    @NotBlank(message = "주소는 필수입니다.")
    private String address;

    private List<OrderItemRequest> items;
}
