package com.terracota.infrastructure.user.company.presenter;

import com.terracota.application.company.retrieve.get.CompanyOutput;
import com.terracota.infrastructure.user.company.models.CompanyResponse;

public interface CompanyPresenter {

    static CompanyResponse present(final CompanyOutput output) {
        return new CompanyResponse(
                output.id(),
                output.ownerEmail(),
                output.legalName(),
                output.tradeName(),
                output.cnpj(),
                output.phone(),
                output.isActive(),
                output.photo(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}
