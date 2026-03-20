package com.back.code_brew.order.controller;

import com.back.code_brew.order.entity.Order;
import com.back.code_brew.order.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    record OrderCreateRequestForm(
            @NotBlank(message = "01-name-이름은 필수입니다.")
            String name,

            @Email(message = "02-email-올바른 이메일 형식이어야 합니다.")
            @NotBlank(message = "03-email-이메일은 필수입니다.")
            String email,

            @NotBlank(message = "04-address-주소는 필수입니다.")
            String address
    ){}

    @GetMapping("/orders/create")
    public String createForm(@ModelAttribute("form") OrderCreateRequestForm form) {
        return "create";
    }

    @PostMapping("/orders")
    public String create(
            @Valid @ModelAttribute("form") OrderCreateRequestForm form,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "create";
        }

        Order order = orderService.create(
                form.name(),
                form.email(),
                form.address()
        );

        return "redirect:/orders/%d".formatted(order.getId());
    }

    @GetMapping("/orders")
    public String list() {
        return "orders/list";
    }
}

