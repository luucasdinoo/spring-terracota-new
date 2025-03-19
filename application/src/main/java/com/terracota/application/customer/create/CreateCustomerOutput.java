package com.terracota.application.customer.create;

import com.terracota.domain.user.customer.Customer;

public record CreateCustomerOutput(String id) {
    public static CreateCustomerOutput from(final Customer aCustomer){
        return new CreateCustomerOutput(aCustomer.getId().getValue());
    }
}
