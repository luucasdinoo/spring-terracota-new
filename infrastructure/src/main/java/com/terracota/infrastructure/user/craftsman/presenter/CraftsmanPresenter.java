package com.terracota.infrastructure.user.craftsman.presenter;

import com.terracota.application.craftsman.retrieve.get.CraftsmanOutput;
import com.terracota.application.craftsman.retrieve.list.ListCraftsmenOutput;
import com.terracota.domain.user.Role;
import com.terracota.infrastructure.user.craftsman.models.CraftsmanResponse;
import com.terracota.infrastructure.user.craftsman.models.ListCraftsmenResponse;

public interface CraftsmanPresenter {

    static ListCraftsmenResponse present(final ListCraftsmenOutput output){
        return new ListCraftsmenResponse(
                output.id(),
                output.email(),
                Role.valueOf(output.role())
        );
    }

    static CraftsmanResponse present(final CraftsmanOutput output){
        return new CraftsmanResponse(
                output.id(),
                output.email(),
                Role.valueOf(output.role()),
                output.name(),
                output.phone(),
                output.cpf(),
                output.isActive(),
                output.photo(),
                output.address(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
