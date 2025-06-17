package com.terracota.application.customer.retrieve.list;

import com.terracota.domain.user.customer.Customer;

public record ListCustomerOutput(
        String id,
        String email,
        String role
) {
    public static ListCustomerOutput from(final Customer customer){
        return new ListCustomerOutput(
                customer.getId().getValue(),
                customer.getUser().getEmail(),
                customer.getUser().getRole().name()
        );
    }
}
