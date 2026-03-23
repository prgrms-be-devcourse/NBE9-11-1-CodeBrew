package com.back.code_brew.domain.admin.controller;

import com.back.code_brew.domain.admin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

//    record LoginReqBody(
//            @NotBlank(message = "아이디 입력")
//            String adminName,
//
//            @NotBlank(message = "비밀번호 입력")
//            String password
//    ) {
//    }
//
//    record LoginResBody(
//        String token,
//        AdminDto adminDto
//    ) {
//    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {
        String token = authService.login(req.get("adminName"), req.get("password"));
        return Map.of("token", token);
    }
}
