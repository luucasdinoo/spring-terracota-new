package com.terracota.application.company.management.remove;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultRemoveCraftsmanUseCase extends RemoveCraftsmanUseCase {

    private final CraftsmanGateway craftsmanGateway;

    public DefaultRemoveCraftsmanUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public void execute(final RemoveCraftsmanCommand input) {
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        Craftsman craftsman = this.craftsmanGateway.findById(craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, craftsmanId));

        craftsman.setCompany(null);
        this.craftsmanGateway.update(craftsman);
    }
}
