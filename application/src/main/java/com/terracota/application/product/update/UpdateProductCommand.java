package com.terracota.application.product.update;

public record UpdateProductCommand(
        String productId,
        String craftsmanId,
        String name,
        String description,
        double price
) {
    public static UpdateProductCommand with(
            final String productId,
            final String craftsmanId,
            final String name,
            final String description,
            final double price
    ){
        return new UpdateProductCommand(productId,craftsmanId, name, description, price);
    }
}
