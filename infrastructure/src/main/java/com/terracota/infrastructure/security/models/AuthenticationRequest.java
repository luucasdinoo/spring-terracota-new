package com.terracota.infrastructure.security.models;

public record AuthenticationRequest(
        String email,
        String password
) {
}
