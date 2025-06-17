package com.terracota.application.company.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;

import java.util.Objects;

public class DefaultGetCompanyByIdUseCase extends GetCompanyByIdUseCase {

    private final CompanyGateway companyGateway;

    public DefaultGetCompanyByIdUseCase(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Override
    public CompanyOutput execute(final String anId) {
        CompanyID companyId = CompanyID.from(anId);
        return this.companyGateway.findById(companyId)
                .map(CompanyOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Company.class, anId));
    }
}
