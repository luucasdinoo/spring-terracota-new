package com.terracota.customer.update;

public record UpdateCustomerCommand(
        String id,
        String name,
        String phone,
        boolean isActive
) {
}
