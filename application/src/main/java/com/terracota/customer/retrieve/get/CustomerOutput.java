package com.terracota.customer.retrieve.get;

import com.terracota.customer.Address;
import com.terracota.customer.Customer;

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
                customer.getEmail(),
                customer.getRole().name(),
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
