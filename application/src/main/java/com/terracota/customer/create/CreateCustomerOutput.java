package com.terracota.customer.create;

import com.terracota.customer.Customer;

public record CreateCustomerOutput(String id) {
    public static CreateCustomerOutput from(final Customer aCustomer){
        return new CreateCustomerOutput(aCustomer.getId().getValue());
    }
}
