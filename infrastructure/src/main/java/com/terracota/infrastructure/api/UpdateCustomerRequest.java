package com.terracota.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCustomerRequest(
        String name,
        String phone,
        @JsonProperty("is_active") boolean isActive
) {
}
