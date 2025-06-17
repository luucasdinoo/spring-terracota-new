package com.terracota.application.craftsman.create;

import com.terracota.domain.user.craftsman.Craftsman;

public record CreateCraftsmanOutput(String id) {
    public static CreateCraftsmanOutput from(final Craftsman aCraftsman){
        return new CreateCraftsmanOutput(aCraftsman.getId().getValue());
    }
}
