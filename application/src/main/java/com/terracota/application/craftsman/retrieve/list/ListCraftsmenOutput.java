package com.terracota.application.craftsman.retrieve.list;

import com.terracota.domain.user.craftsman.Craftsman;

public record ListCraftsmenOutput(
        String id,
        String email,
        String role
) {
    public static ListCraftsmenOutput from(final Craftsman craftsman){
        return new ListCraftsmenOutput(
                craftsman.getId().getValue(),
                craftsman.getUser().getEmail(),
                craftsman.getUser().getRole().name()
        );
    }
}
