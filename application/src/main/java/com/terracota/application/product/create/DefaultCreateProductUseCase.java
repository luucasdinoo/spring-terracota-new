package com.terracota.application.product.create;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductType;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.math.BigDecimal;
import java.util.Objects;

public class DefaultCreateProductUseCase extends CreateProductUseCase{

    private final ProductGateway productGateway;

    private final CraftsmanGateway craftsmanGateway;

    public DefaultCreateProductUseCase(
            final ProductGateway productGateway,
            final CraftsmanGateway craftsmanGateway
    ) {
        this.productGateway = Objects.requireNonNull(productGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public CreateProductOutput execute(final CreateProductCommand input) {
        ProductType type = ProductType.of(input.type())
                .orElseThrow(() -> DomainException.with("Invalid product type"));

        CraftsmanID anId = CraftsmanID.from(input.craftsmanId());
        Craftsman craftsman = craftsmanGateway.findById(anId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, anId));

        Product product = Product.newProduct(
                input.name(),
                input.description(),
                BigDecimal.valueOf(input.price()),
                type,
                null,
                craftsman
        );
        return CreateProductOutput.from(productGateway.create(product));
    }
}
