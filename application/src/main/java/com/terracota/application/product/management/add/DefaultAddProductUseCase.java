package com.terracota.application.product.management.add;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.exceptions.InvalidInputException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultAddProductUseCase extends AddProductUseCase {

    private final ProductGateway productGateway;

    public DefaultAddProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public void execute(final AddProductCommand input) {
        ProductID productId = ProductID.from(input.productId());
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        Product product = this.productGateway.findById(productId, craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Product.class, productId));

        if (input.quantity() < 0){
            throw InvalidInputException.with("The quantity must be greater than or equal to 0");
        }

        product.add(input.quantity());
        this.productGateway.update(product);
    }
}
