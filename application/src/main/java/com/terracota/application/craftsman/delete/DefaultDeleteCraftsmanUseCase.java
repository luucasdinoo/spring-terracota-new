package com.terracota.application.craftsman.delete;

import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultDeleteCraftsmanUseCase extends DeleteCraftsmanUseCase{

    private final CraftsmanGateway craftsmanGateway;

    public DefaultDeleteCraftsmanUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public void execute(final String anId) {
        this.craftsmanGateway.deleteById(CraftsmanID.from(anId));
    }
}
