package com.terracota.application.product.create;

public record CreateProductCommand(
        String name,
        String description,
        double price,
        int quantity,
        String type,
        String craftsmanId
) {
    public static CreateProductCommand with(
            final String name,
            final String description,
            final double price,
            final int quantity,
            final String type,
            final String craftsmanId
    ){
        return new CreateProductCommand(name, description, price, quantity, type, craftsmanId);
    }
}
