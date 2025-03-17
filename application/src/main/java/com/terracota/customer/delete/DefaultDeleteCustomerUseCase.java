package com.terracota.customer.delete;

import com.terracota.customer.CustomerGateway;
import com.terracota.user.UserID;

import java.util.Objects;

public class DefaultDeleteCustomerUseCase extends DeleteCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultDeleteCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public void execute(String anId) {
        this.customerGateway.deleteById(UserID.from(anId));
    }
}
