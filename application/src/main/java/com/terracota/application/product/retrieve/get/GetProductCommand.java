package com.terracota.application.product.retrieve.get;

public record GetProductCommand(String productId, String craftsmanId) {
    public static GetProductCommand with(final String productId, final String craftsmanId){
        return new GetProductCommand(productId, craftsmanId);
    }
}
