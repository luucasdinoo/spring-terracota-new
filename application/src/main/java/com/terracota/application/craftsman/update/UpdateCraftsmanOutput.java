package com.terracota.application.craftsman.update;

import com.terracota.domain.user.craftsman.Craftsman;

public record UpdateCraftsmanOutput(String id){
    public static UpdateCraftsmanOutput from(final Craftsman craftsman){
        return new UpdateCraftsmanOutput(craftsman.getId().getValue());
    }
}
