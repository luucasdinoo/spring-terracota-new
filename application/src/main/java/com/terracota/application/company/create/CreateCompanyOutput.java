package com.terracota.application.company.create;

import com.terracota.domain.user.company.Company;

public record CreateCompanyOutput(String id) {
    public static CreateCompanyOutput from(final Company company) {
        return new CreateCompanyOutput(company.getId().getValue());
    }
}
