package com.terracota.infrastructure.user.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terracota.domain.user.Address;
import com.terracota.domain.user.Role;

import java.time.Instant;

public record CustomerResponse(
        String id,
        String email,
        Role role,
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
