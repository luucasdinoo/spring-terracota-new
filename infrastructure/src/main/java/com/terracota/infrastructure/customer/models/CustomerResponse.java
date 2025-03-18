package com.terracota.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terracota.customer.Address;
import com.terracota.user.UserRole;

import java.time.Instant;

public record CustomerResponse(
        String id,
        String email,
        UserRole role,
        String name,
        String phone,
        String cpf,
        @JsonProperty("is_active")boolean isActive,
        String photo,
        Address address,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
