package com.terracota.application.customer.retrieve.get;

import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.Address;

import java.time.Instant;

public record CustomerOutput(
        String id,
        String email,
        String role,
        String name,
        String phone,
        String cpf,
        boolean isActive,
        String photo,
        Address address,
        Instant createdAt,
        Instant updatedAt
) {

    public static CustomerOutput from(final Customer customer){
        return new CustomerOutput(
                customer.getId().getValue(),
                customer.getUser().getEmail(),
                customer.getUser().getRole().name(),
                customer.getName(),
                customer.getPhone(),
                customer.getCpf().getValue(),
                customer.isActive(),
                null,
                null,
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
