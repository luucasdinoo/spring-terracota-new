package com.terracota.application.company.create;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.user.CNPJ;
import com.terracota.domain.user.Role;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;

import java.util.Objects;

public class DefaultCreateCompanyUseCase extends CreateCompanyUseCase {

    private final CompanyGateway companyGateway;

    public DefaultCreateCompanyUseCase(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Override
    public CreateCompanyOutput execute(final CreateCompanyCommand input) {
        Role role = Role.of(input.role())
                .orElseThrow(() -> DomainException.with("Invalid role"));

        if (role == Role.ADMIN)
            throw DomainException.with("Invalid role");

        Company company = Company.newCompany(
                input.email(),
                input.password(),
                role,
                input.legalName(),
                input.tradeName(),
                CNPJ.from(input.cnpj()),
                input.phone(),
                input.active()
        );

        return CreateCompanyOutput.from(this.companyGateway.create(company));
    }
}
