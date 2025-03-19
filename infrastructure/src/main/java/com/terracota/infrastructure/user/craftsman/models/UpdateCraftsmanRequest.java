package com.terracota.infrastructure.user.craftsman.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCraftsmanRequest(
        String name,
        String phone,
        @JsonProperty("is_active") boolean isActive
) {
}
