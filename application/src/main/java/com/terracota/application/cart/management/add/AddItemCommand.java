package com.terracota.application.cart.management.add;

public record AddItemCommand(
        String productId,
        String craftsmanId,
        String customerId,
        int quantity
) {
    public static AddItemCommand with(
            String productId,
            String craftsmanId,
            String customerId,
            int quantity
    ) {
        return new AddItemCommand(productId, craftsmanId, customerId, quantity);
    }
}
