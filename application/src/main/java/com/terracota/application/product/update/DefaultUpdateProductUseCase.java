package com.terracota.application.product.update;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.math.BigDecimal;
import java.util.Objects;

public class DefaultUpdateProductUseCase extends UpdateProductUseCase{

    private final ProductGateway productGateway;

    public DefaultUpdateProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public UpdateProductOutput execute(final UpdateProductCommand input) {
        ProductID productId = ProductID.from(input.productId());
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        Product product = this.productGateway.findById(productId, craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Product.class, productId));

        Product updatedProduct = product.update(
                input.name(),
                input.description(),
                BigDecimal.valueOf(input.price())
        );

        return UpdateProductOutput.from(this.productGateway.update(updatedProduct));
    }
}
