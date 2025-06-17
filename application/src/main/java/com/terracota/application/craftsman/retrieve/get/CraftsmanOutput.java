package com.terracota.application.craftsman.retrieve.get;

import com.terracota.domain.resource.ImagePhoto;
import com.terracota.domain.user.Address;
import com.terracota.domain.user.craftsman.Craftsman;

import java.time.Instant;

public record CraftsmanOutput(
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

    public static CraftsmanOutput from(final Craftsman craftsman){
        return new CraftsmanOutput(
                craftsman.getId().getValue(),
                craftsman.getUser().getEmail(),
                craftsman.getUser().getRole().name(),
                craftsman.getName(),
                craftsman.getPhone(),
                craftsman.getCpf().getValue(),
                craftsman.isActive(),
                craftsman.getPhoto()
                        .map(ImagePhoto::location)
                        .orElse(null),
                craftsman.getAddress()
                        .map(Address::with)
                        .orElse(null),
                craftsman.getCreatedAt(),
                craftsman.getUpdatedAt()
        );
    }
}
