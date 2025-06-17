package com.terracota.infrastructure.user.craftsman.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terracota.infrastructure.user.customer.models.AddressRequest;

public record CreateCraftsmanRequest(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("user_role")String role,
        @JsonProperty("name") String name,
        @JsonProperty("phone") String phone,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("is_active")Boolean isActive,
        @JsonProperty("address") AddressRequest address
) {
}
