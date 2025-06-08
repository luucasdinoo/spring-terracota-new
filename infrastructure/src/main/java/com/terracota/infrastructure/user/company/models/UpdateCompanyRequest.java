package com.terracota.infrastructure.user.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCompanyRequest(
        @JsonProperty("legal_name") String legalName,
        @JsonProperty("trade_name") String tradeName,
        String phone,
        @JsonProperty("is_active") boolean isActive
) {
}
