package com.terracota.application.craftsman.update;

import com.terracota.application.customer.update.UpdateCustomerOutput;
import com.terracota.domain.user.craftsman.Craftsman;

public record UpdateCraftsmanOutput(String id){
    public static UpdateCustomerOutput from(final Craftsman craftsman){
        return new UpdateCustomerOutput(craftsman.getId().getValue());
    }
}
