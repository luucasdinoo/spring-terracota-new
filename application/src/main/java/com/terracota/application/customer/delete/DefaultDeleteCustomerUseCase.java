package com.terracota.application.customer.delete;

import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.util.Objects;

public class DefaultDeleteCustomerUseCase extends DeleteCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultDeleteCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public void execute(String anId) {
        this.customerGateway.deleteById(CustomerID.from(anId));
    }
}
