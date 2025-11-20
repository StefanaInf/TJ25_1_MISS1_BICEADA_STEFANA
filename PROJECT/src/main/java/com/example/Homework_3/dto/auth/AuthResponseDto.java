package com.example.Homework_3.dto.auth;

import com.example.Homework_3.domain.Role;

public record AuthResponseDto (String token, String email, Role role) {
}
