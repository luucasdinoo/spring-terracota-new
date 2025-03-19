package com.terracota.application.customer.update;

import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;
import com.terracota.domain.exceptions.EntityNotFoundException;

import java.util.Objects;

public class DefaultUpdateCustomerUseCase extends UpdateCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultUpdateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public UpdateCustomerOutput execute(final UpdateCustomerCommand input) {
        CustomerID customerID = CustomerID.from(input.id());
        Customer customer = this.customerGateway.findById(customerID)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, customerID));
        Customer updatedCustomer = customer.update(input.name(), input.phone(), input.isActive());

        return UpdateCustomerOutput.from(this.customerGateway.update(updatedCustomer));
    }
}
