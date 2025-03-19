package com.terracota.application.customer.create;

import com.terracota.domain.user.Address;

public record CreateCustomerCommand(
        String email,
        String password,
        String role,
        String name,
        String phone,
        boolean isActive,
        String cpf,
        Address address
) {

    public static CreateCustomerCommand with(
            final String email,
            final String password,
            final String role,
            final String name,
            String phone,
            boolean isActive,
            final String cpf,
            final Address address
    ){
        return new CreateCustomerCommand(email, password, role, name, phone, isActive, cpf, address);
    }
}
