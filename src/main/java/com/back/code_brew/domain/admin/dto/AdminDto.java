package com.back.code_brew.domain.admin.dto;

import com.back.code_brew.domain.admin.entity.Admin;

public record AdminDto(
        int id,
        String username,
        String role
) {
    public AdminDto(Admin admin) {
        this(
                admin.getId(),
                admin.getUsername(),
                admin.getRole()
        );
    }
}
