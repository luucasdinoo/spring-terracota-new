package com.terracota.customer.retrieve.get;

import com.terracota.customer.Customer;
import com.terracota.customer.CustomerGateway;
import com.terracota.exceptions.EntityNotFoundException;
import com.terracota.user.UserID;

import java.util.Objects;

public class DefaultGetCustomerBydIdUseCase extends GetCustomerBydIdUseCase{

    private final CustomerGateway customerGateway;

    public DefaultGetCustomerBydIdUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CustomerOutput execute(final String anId) {
        UserID userId = UserID.from(anId);
        return this.customerGateway.findById(userId)
                .map(CustomerOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, userId));
    }
}
