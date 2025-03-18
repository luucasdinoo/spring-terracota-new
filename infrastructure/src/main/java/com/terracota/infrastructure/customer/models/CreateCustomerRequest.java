package com.terracota.infrastructure.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCustomerRequest(
        @JsonProperty("email") String email,
        @JsonProperty("password") String password,
        @JsonProperty("user_role")String role,
        @JsonProperty("name") String name,
        @JsonProperty("phone") String phone,
        @JsonProperty("cpf") String cpf,
        @JsonProperty("is_active")Boolean isActive,
        @JsonProperty("address")AddressRequest address
) {
}
