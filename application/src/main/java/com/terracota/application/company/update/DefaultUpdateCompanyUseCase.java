package com.terracota.application.company.update;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;

import java.util.Objects;

public class DefaultUpdateCompanyUseCase extends UpdateCompanyUseCase{

    private final CompanyGateway companyGateway;

    public DefaultUpdateCompanyUseCase(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Override
    public UpdateCompanyOutput execute(final UpdateCompanyCommand input) {
        CompanyID anId = CompanyID.from(input.id());
        Company company = this.companyGateway.findById(anId)
                .orElseThrow(() -> EntityNotFoundException.with(Company.class, anId));
        Company updatedCompany = company.update(input.legalName(), input.tradeName(), input.phone(), input.isActive());
        return UpdateCompanyOutput.from(this.companyGateway.update(updatedCompany));
    }
}
