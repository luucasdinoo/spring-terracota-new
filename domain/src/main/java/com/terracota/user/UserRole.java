package com.terracota.user;

public enum UserRole {

    CUSTOMER("customer"),
    CRAFTSMAN("craftsman"),
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return "ROLE_" + role.toUpperCase();
    }
}
