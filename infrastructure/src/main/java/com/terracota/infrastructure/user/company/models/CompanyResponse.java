package com.terracota.infrastructure.user.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record CompanyResponse(
        String id,
        @JsonProperty("owner_email") String ownerEmail,
        @JsonProperty("legal_name") String legalName,
        @JsonProperty("trade_name") String tradeName,
        String cnpj,
        String phone,
        @JsonProperty("is_active") boolean isActive,
        String photo,
        @JsonProperty("created_at")Instant createdAt,
        @JsonProperty("updated_at")Instant updatedAt
) {
}
