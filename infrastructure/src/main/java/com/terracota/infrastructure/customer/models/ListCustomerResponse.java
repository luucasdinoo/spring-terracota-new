package com.terracota.infrastructure.customer.models;

import com.terracota.user.UserRole;

public record ListCustomerResponse(
        String id,
        String email,
        UserRole role
) {
}
