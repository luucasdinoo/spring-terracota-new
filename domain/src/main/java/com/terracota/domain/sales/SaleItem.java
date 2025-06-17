package com.terracota.domain.sales;

public record SaleItem(
        String productId,
        Integer quantity
) {
    public static SaleItem with(final String productId, final Integer quantity) {
        return new SaleItem(productId, quantity);
    }
}
