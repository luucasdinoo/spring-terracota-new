package com.terracota.application.craftsman.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;

import java.util.Objects;

public class DefaultGetCraftsmanByEmailUseCase extends GetCraftsmanByEmailUseCase{

    private final CraftsmanGateway craftsmanGateway;

    public DefaultGetCraftsmanByEmailUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public CraftsmanOutput execute(String email) {
        return this.craftsmanGateway.findByEmail(email)
                .map(CraftsmanOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, email));
    }
}
