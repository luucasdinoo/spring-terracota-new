package com.terracota.domain.sales;

import com.terracota.domain.product.ProductID;

public record SaleItem(
        ProductID productId,
        Integer quantity
) {
    public static SaleItem with(final ProductID productId, final Integer quantity) {
        return new SaleItem(productId, quantity);
    }
}
