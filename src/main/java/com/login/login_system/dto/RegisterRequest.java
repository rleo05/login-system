package com.login.login_system.dto;

import com.login.login_system.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Name cannot be empty or null")
        @Size(min = 4, max = 40, message = "Name must be between 6 and 40 characters")
        String name,
        @NotBlank(message = "Email cannot be empty or null")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Password cannot be empty or null")
        @Size(min = 8, message = "Password must contain at least 8 characters")
        String password,
        @NotNull(message = "Role cannot be null")
        UserRole role) {
}
