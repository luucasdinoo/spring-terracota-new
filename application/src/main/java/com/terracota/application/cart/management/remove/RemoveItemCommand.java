package com.terracota.application.cart.management.remove;

public record RemoveItemCommand(
        String productId,
        String craftsmanId,
        String customerId,
        int quantity
) {
    public static RemoveItemCommand with(
            String productId,
            String craftsmanId,
            String customerId,
            int quantity
    ) {
        return new RemoveItemCommand(productId, craftsmanId, customerId, quantity);
    }
}
