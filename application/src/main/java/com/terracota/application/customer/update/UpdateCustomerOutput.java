package com.terracota.application.customer.update;

import com.terracota.domain.user.customer.Customer;

public record UpdateCustomerOutput(String id) {
    public static UpdateCustomerOutput from(final Customer aCustomer){
        return new UpdateCustomerOutput(aCustomer.getId().getValue());
    }
}
