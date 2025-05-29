package com.terracota.infrastructure.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ListProductResponse(
        String id,
        String name,
        String description,
        Double price,
        String type,
        Integer quantity,
        String photo,
        @JsonProperty("craftsman_id") String craftsmanId
) {
}
