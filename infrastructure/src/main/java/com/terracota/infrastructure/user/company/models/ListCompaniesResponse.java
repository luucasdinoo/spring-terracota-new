package com.terracota.infrastructure.user.company.models;

public record ListCompaniesResponse(
        String legalName,
        String tradeName,
        String cnpj
) {
}
