package com.terracota.application.craftsman.create;

import com.terracota.domain.user.Address;

public record CreateCraftsmanCommand(
        String email,
        String password,
        String role,
        String name,
        String phone,
        boolean isActive,
        String cpf,
        Address address
) {

    public static CreateCraftsmanCommand with(
            final String email,
            final String password,
            final String role,
            final String name,
            String phone,
            boolean isActive,
            final String cpf,
            final Address address
    ){
        return new CreateCraftsmanCommand(email, password, role, name, phone, isActive, cpf, address);
    }
}
