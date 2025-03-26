package com.terracota.application.product.retrieve.get;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultGetProductByIdUseCase extends GetProductByIdUseCase{

    private final ProductGateway productGateway;

    public DefaultGetProductByIdUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public ProductOutput execute(final GetProductCommand input) {
        ProductID productId = ProductID.from(input.productId());
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());

        return this.productGateway.findById(productId, craftsmanId)
                .map(ProductOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Product.class, productId));
    }
}
