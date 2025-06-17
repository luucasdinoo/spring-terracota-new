package com.terracota.application.product.management.add;

public record AddProductCommand(
        String productId,
        String craftsmanId,
        int quantity
) {
    public static AddProductCommand with(
            final String productId,
            final String craftsmanId,
            final int quantity
    ){
        return new AddProductCommand(productId, craftsmanId, quantity);
    }
}
