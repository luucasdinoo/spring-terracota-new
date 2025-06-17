package com.terracota.application.craftsman.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultGetCraftsmanByIdUseCase extends GetCraftsmanByIdUseCase{

    private final CraftsmanGateway craftsmanGateway;

    public DefaultGetCraftsmanByIdUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public CraftsmanOutput execute(final String input) {
        CraftsmanID anId = CraftsmanID.from(input);
        return this.craftsmanGateway.findById(anId)
                .map(CraftsmanOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, anId));
    }
}
