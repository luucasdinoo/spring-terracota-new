package com.terracota.infrastructure.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terracota.domain.product.ProductType;

import java.time.Instant;

public record ProductResponse(
        String id,
        String name,
        String description,
        Double price,
        Integer quantity,
        ProductType type,
        String photo,
        @JsonProperty("craftsman_id") String craftsmanId,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
