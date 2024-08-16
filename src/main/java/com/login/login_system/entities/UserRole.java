package com.login.login_system.entities;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    final String role;

    UserRole(String role) {
        this.role = role;
    }
}
