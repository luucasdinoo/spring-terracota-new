package com.terracota.application.cart.management.add;

public record AddItemCommand(
        String productId,
        String craftsmanId,
        String customerId,
        int quantity
) {
}
