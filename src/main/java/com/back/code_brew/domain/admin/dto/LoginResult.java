package com.back.code_brew.domain.admin.dto;

import com.back.code_brew.domain.admin.entity.Admin;

public record LoginResult(
        String token,
        Admin admin
) {
}
