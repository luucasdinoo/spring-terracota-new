package com.terracota.infrastructure.user.customer.models;

import com.terracota.domain.user.Role;

public record ListCustomerResponse(
        String id,
        String email,
        Role role
) {
}
