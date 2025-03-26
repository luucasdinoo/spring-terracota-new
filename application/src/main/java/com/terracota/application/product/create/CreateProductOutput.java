package com.terracota.application.product.create;

import com.terracota.domain.product.Product;

public record CreateProductOutput(String id) {
    public static CreateProductOutput from(final Product product){
        return new CreateProductOutput(product.getId().getValue());
    }
}
