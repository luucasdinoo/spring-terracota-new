package com.terracota.infrastructure.stock.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddRemoveItemRequest(
        @JsonProperty("stock_id") String stockId,
        @JsonProperty("product_id") String productId,
        Integer quantity,
        String operation
) {
}
