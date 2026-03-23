package com.back.code_brew.domain.admin.controller;

import com.back.code_brew.domain.admin.dto.AdminDto;
import com.back.code_brew.domain.admin.dto.LoginDto;
import com.back.code_brew.domain.admin.service.AdminService;
import com.back.code_brew.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AdminController {
    private final AdminService adminService;

    record LoginReqBody(
            @NotBlank(message = "아이디를 입력하세요.")
            String username,

            @NotBlank(message = "비밀번호를 입력하세요.")
            String password
    ) {
    }

    record LoginResBody(
        String token,
        AdminDto adminDto
    ) {
    }

    @PostMapping("/login")
    public RsData<LoginResBody> login(@RequestBody @Valid LoginReqBody reqBody) {
        LoginDto result = adminService.login(reqBody.username(), reqBody.password());

        return new RsData<>(
                "로그인 성공",
                "200-1",
                new LoginResBody(
                        result.token(),
                        new AdminDto(result.admin())
                )
        );
    }
}
