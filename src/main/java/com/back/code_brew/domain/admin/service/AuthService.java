package com.back.code_brew.domain.admin.service;

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

    public String login(String adminName, String password) {
        Admin admin = adminRepository.findByAdminName(adminName)
                .orElseThrow(() -> new RuntimeException("없는 관리자"));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("비번 틀림");
        }

        return jwtUtil.createToken(admin.getAdminName(), admin.getRole());
    }
}
