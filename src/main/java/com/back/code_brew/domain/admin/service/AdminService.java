package com.back.code_brew.domain.admin.service;

import com.back.code_brew.domain.admin.dto.LoginDto;
import com.back.code_brew.domain.admin.entity.Admin;
import com.back.code_brew.domain.admin.repository.AdminRepository;
import com.back.code_brew.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    public LoginDto login(String username, String password) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        String token = jwtUtil.createToken(admin.getUsername(), admin.getRole());

        return new LoginDto(token, admin);
    }
}
