package com.terracota.customer.update;

import com.terracota.customer.Customer;

public record UpdateCustomerOutput(String id) {
    public static UpdateCustomerOutput from(final Customer aCustomer){
        return new UpdateCustomerOutput(aCustomer.getId().getValue());
    }
}
