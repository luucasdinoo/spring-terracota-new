package com.terracota.infrastructure.user.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCompanyRequest(
        String email,
        String password,
        String role,
        @JsonProperty("legal_name")String legalName,
        @JsonProperty("trade_name") String tradeName,
        String cnpj,
        String phone,
        Boolean active
) {
}
