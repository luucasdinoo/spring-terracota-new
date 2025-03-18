package com.terracota.customer.update;

import com.terracota.customer.Customer;
import com.terracota.customer.CustomerGateway;
import com.terracota.exceptions.EntityNotFoundException;
import com.terracota.user.UserID;

import java.util.Objects;

public class DefaultUpdateCustomerUseCase extends UpdateCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultUpdateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public UpdateCustomerOutput execute(final UpdateCustomerCommand input) {
        UserID anId = UserID.from(input.id());
        Customer customer = this.customerGateway.findById(anId)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, anId));
        Customer updatedCustomer = customer.update(input.name(), input.phone(), input.isActive());

        return UpdateCustomerOutput.from(this.customerGateway.update(updatedCustomer));
    }
}
