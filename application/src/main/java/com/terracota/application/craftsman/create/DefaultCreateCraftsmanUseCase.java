package com.terracota.application.craftsman.create;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.user.CPF;
import com.terracota.domain.user.Role;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;

import java.util.Objects;

public class DefaultCreateCraftsmanUseCase extends CreateCraftsmanUseCase{

    private final CraftsmanGateway craftsmanGateway;

    public DefaultCreateCraftsmanUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public CreateCraftsmanOutput execute(final CreateCraftsmanCommand input) {
        Role role = Role.of(input.role())
                .orElseThrow(() -> DomainException.with("Invalid role"));

        //TODO: encrypt password

        Craftsman craftsman = Craftsman.newCraftsman(
                input.email(),
                input.password(),
                role,
                input.name(),
                input.phone(),
                CPF.from(input.cpf()),
                input.isActive(),
                null,
                input.address()
        );
        return CreateCraftsmanOutput.from(this.craftsmanGateway.create(craftsman));
    }
}
