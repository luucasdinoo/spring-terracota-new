package com.terracota.application.product.management.remove;

public record RemoveProductCommand(
        String productId,
        String craftsmanId,
        int quantity
) {
    public static RemoveProductCommand with(
            final String productId,
            final String craftsmanId,
            final int quantity
    ){
        return new RemoveProductCommand(productId, craftsmanId, quantity);
    }
}
