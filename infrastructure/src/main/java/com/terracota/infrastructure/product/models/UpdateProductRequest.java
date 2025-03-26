package com.terracota.infrastructure.product.models;

public record UpdateProductRequest(
        String name,
        String description,
        Double price
) {
}
