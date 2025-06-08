package com.terracota.infrastructure.user.company.presenter;

import com.terracota.application.company.retrieve.get.CompanyOutput;
import com.terracota.application.company.retrieve.list.ListCompaniesOutput;
import com.terracota.infrastructure.user.company.models.CompanyResponse;
import com.terracota.infrastructure.user.company.models.ListCompaniesResponse;

public interface CompanyPresenter {

    static CompanyResponse present(final CompanyOutput output) {
        return new CompanyResponse(
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

    static ListCompaniesResponse present(final ListCompaniesOutput output){
        return new ListCompaniesResponse(
                output.legalName(),
                output.tradeName(),
                output.cnpj()
        );
    }
}
