package com.terracota.domain.user;

import java.util.Arrays;
import java.util.Optional;

public enum Role {

    CUSTOMER("customer"),
    CRAFTSMAN("craftsman"),
    COMPANY("company"),
    ADMIN("admin");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public static Optional<Role> of(final String role) {
        return Arrays.stream(Role.values())
                .filter(r -> r.role.equalsIgnoreCase(role))
                .findFirst();
    }

    public String getRole() {
        return "ROLE_" + role.toUpperCase();
    }
}
