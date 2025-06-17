package com.terracota.infrastructure.user.craftsman.models;

import com.terracota.domain.user.Role;

public record ListCraftsmenResponse(
        String id,
        String email,
        Role role
) {
}
