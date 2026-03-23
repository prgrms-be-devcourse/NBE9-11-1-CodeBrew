package com.back.code_brew.domain.admin.service;

import com.back.code_brew.domain.admin.dto.LoginResult;
import com.back.code_brew.domain.admin.entity.Admin;
import com.back.code_brew.domain.admin.repository.AdminRepository;
import com.back.code_brew.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    public LoginResult login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("없는 관리자"));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("비번 틀림");
        }

        String token = jwtUtil.createToken(admin.getUsername(), admin.getRole());

        return new LoginResult(token, admin);
    }
}
