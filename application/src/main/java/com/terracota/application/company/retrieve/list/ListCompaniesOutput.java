package com.terracota.application.company.retrieve.list;

import com.terracota.domain.user.company.Company;

public record ListCompaniesOutput(
        String legalName,
        String tradeName,
        String cnpj
) {
    public static ListCompaniesOutput from(final Company company) {
        return new ListCompaniesOutput(
                company.getLegalName(),
                company.getTradeName(),
                company.getCnpj().getValue()
        );
    }
}
