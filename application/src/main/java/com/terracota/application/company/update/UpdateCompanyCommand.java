package com.terracota.application.company.update;

public record UpdateCompanyCommand(
        String id,
        String legalName,
        String tradeName,
        String phone,
        boolean isActive
) {
    public static UpdateCompanyCommand with(
            final String id,
            final String legalName,
            final String tradeName,
            final String phone,
            final boolean isActive
    ){
        return new  UpdateCompanyCommand(id, legalName, tradeName, phone, isActive);
    }
}
