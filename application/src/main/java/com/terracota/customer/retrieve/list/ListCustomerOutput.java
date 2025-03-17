package com.terracota.customer.retrieve.list;

import com.terracota.customer.Customer;

public record ListCustomerOutput(
        String id,
        String email,
        String role
) {
    public static ListCustomerOutput from(final Customer customer){
        return new ListCustomerOutput(
                customer.getId().getValue(),
                customer.getEmail(),
                customer.getRole().name()
        );
    }
}
