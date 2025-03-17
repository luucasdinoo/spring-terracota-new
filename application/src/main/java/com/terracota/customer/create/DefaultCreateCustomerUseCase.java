package com.terracota.customer.create;

import com.terracota.customer.CPF;
import com.terracota.customer.Customer;
import com.terracota.customer.CustomerGateway;
import com.terracota.exceptions.DomainException;
import com.terracota.user.UserRole;

import java.util.Objects;

public class DefaultCreateCustomerUseCase extends CreateCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultCreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CreateCustomerOutput execute(final CreateCustomerCommand input) {
        UserRole userRole = UserRole.of(input.role())
                .orElseThrow(() -> DomainException.with("Invalid role"));

        Customer aCustomer = Customer.newCustomer(
                input.email(),
                input.password(),
                userRole,
                input.name(),
                input.phone(),
                CPF.from(input.cpf()),
                input.isActive());

        return CreateCustomerOutput.from(this.customerGateway.create(aCustomer));
    }
}
