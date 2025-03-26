package com.terracota.application.product.update;

import com.terracota.domain.product.Product;

public record UpdateProductOutput(String id) {
    public static UpdateProductOutput from(final Product product){
        return new UpdateProductOutput(product.getId().getValue());
    }
}
