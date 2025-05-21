package com.terracota.infrastructure.sales.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record CreateSaleRequest(
        @JsonProperty("order_id") String orderId,
        @JsonProperty("craftsman_id") String craftsmanId,
        @JsonProperty("customer_id")String customerId,
        @JsonProperty("products_ids")Set<String> productsIds,
        long total,
        @JsonProperty("payment_method")String paymentMethod,
        String nsu,
        Long aut
) {
}
