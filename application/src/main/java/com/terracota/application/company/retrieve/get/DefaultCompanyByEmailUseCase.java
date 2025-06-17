package com.terracota.application.company.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;

import java.util.Objects;

public class DefaultCompanyByEmailUseCase extends GetCompanyByEmailUseCase {

    private final CompanyGateway companyGateway;

    public DefaultCompanyByEmailUseCase(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Override
    public CompanyOutput execute(final String email) {
        return this.companyGateway.findByEmail(email)
                .map(CompanyOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Company.class, email));
    }
}
