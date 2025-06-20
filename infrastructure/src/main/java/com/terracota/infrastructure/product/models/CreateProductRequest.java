package com.terracota.infrastructure.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateProductRequest(
        String name,
        String description,
        Double price,
        Integer quantity,
        String type,
        @JsonProperty("craftsman_id") String craftsmanId
) {
}
