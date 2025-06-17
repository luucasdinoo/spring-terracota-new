package com.terracota.application.company.create;

public record CreateCompanyCommand(
    String email,
    String password,
    String role,
    String legalName,
    String tradeName,
    String cnpj,
    String phone,
    boolean active
) {
    public static CreateCompanyCommand with(
        final String email,
        final String password,
        final String role,
        final String legalName,
        final String tradeName,
        final String cnpj,
        final String phone,
        final boolean active
    ) {
        return new CreateCompanyCommand(email, password, role, legalName, tradeName, cnpj, phone, active);
    }
}
