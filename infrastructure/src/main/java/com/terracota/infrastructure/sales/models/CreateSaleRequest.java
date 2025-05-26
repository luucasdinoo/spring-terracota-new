package com.terracota.infrastructure.sales.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record CreateSaleRequest(
        @JsonProperty("preference_id") String preferenceId,
        @JsonProperty("payment_id") Long paymentId,
        @JsonProperty("craftsman_id") String craftsmanId,
        @JsonProperty("customer_id")String customerId,
        @JsonProperty("products_ids")Set<String> productsIds,
        Long total,
        @JsonProperty("payment_method")String paymentMethod,
        String status
) {
}
