package com.terracota.user;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {

    CUSTOMER("customer"),
    CRAFTSMAN("craftsman"),
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public static Optional<UserRole> of(final String role) {
        return Arrays.stream(UserRole.values())
                .filter(r -> r.role.equalsIgnoreCase(role))
                .findFirst();
    }

    public String getRole() {
        return "ROLE_" + role.toUpperCase();
    }
}
