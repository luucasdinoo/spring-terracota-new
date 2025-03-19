package com.terracota.application.customer.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.util.Objects;

public class DefaultGetCustomerBydIdUseCase extends GetCustomerBydIdUseCase{

    private final CustomerGateway customerGateway;

    public DefaultGetCustomerBydIdUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(final String anId) {
        CustomerID customerID = CustomerID.from(anId);
        return this.customerGateway.findById(customerID)
                .map(CustomerOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, customerID));
    }
}
