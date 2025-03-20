package com.terracota.application.customer.create;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.user.CPF;
import com.terracota.domain.user.Role;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;

import java.util.Objects;

public class DefaultCreateCustomerUseCase extends CreateCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultCreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CreateCustomerOutput execute(final CreateCustomerCommand input) {
        Role role = Role.of(input.role())
                .orElseThrow(() -> DomainException.with("Invalid role"));

        Customer aCustomer = Customer.newCustomer(
                input.email(),
                input.password(),
                role,
                input.name(),
                input.phone(),
                CPF.from(input.cpf()),
                input.isActive(),
                input.address()
        );

        return CreateCustomerOutput.from(this.customerGateway.create(aCustomer));
    }
}
