package com.terracota.application.customer.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;

import java.util.Objects;

public class DefaultGetCustomerByEmailUseCase extends GetCustomerByEmailUseCase{

    private final CustomerGateway customerGateway;

    public DefaultGetCustomerByEmailUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(String email) {
        return this.customerGateway.findByEmail(email)
                .map(CustomerOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, email));
    }
}
