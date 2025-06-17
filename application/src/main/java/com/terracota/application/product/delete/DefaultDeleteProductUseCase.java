package com.terracota.application.product.delete;

import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;

import java.util.Objects;

public class DefaultDeleteProductUseCase extends DeleteProductUseCase{

    private final ProductGateway productGateway;

    public DefaultDeleteProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public void execute(final String input) {
        this.productGateway.deleteById(ProductID.from(input));
    }
}
