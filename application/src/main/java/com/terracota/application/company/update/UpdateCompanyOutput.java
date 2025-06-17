package com.terracota.application.company.update;

import com.terracota.domain.user.company.Company;

public record UpdateCompanyOutput(String id) {
    public static UpdateCompanyOutput from(final Company aCompany){
        return new UpdateCompanyOutput(aCompany.getId().getValue());
    }
}
