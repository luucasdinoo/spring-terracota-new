package com.terracota.application.craftsman.update;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultUpdateCraftsmanUseCase extends UpdateCraftsmanUseCase{

    private final CraftsmanGateway craftsmanGateway;

    public DefaultUpdateCraftsmanUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public UpdateCraftsmanOutput execute(final UpdateCraftsmanCommand input) {
        CraftsmanID anId = CraftsmanID.from(input.id());
        Craftsman craftsman = this.craftsmanGateway.findById(anId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, anId));

        Craftsman updatedCraftsman = craftsman.update(input.name(), input.phone(), input.isActive());
        this.craftsmanGateway.update(updatedCraftsman);
        return UpdateCraftsmanOutput.from(updatedCraftsman);
    }
}
