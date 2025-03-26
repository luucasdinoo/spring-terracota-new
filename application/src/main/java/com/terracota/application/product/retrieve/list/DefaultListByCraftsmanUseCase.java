package com.terracota.application.product.retrieve.list;

import com.terracota.application.product.retrieve.get.ProductOutput;
import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultListByCraftsmanUseCase extends ListByCraftsmanUseCase{

    private final ProductGateway productGateway;
    private final CraftsmanGateway craftsmanGateway;

    public DefaultListByCraftsmanUseCase(
            final ProductGateway productGateway,
            final CraftsmanGateway craftsmanGateway
    ) {
        this.productGateway = Objects.requireNonNull(productGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public Pagination<ProductOutput> execute(final ListByCraftsmanCommand input) {
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        Craftsman craftsman = this.craftsmanGateway.findById(craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, craftsmanId));

        return this.productGateway.listByCraftsman(craftsman, input.aQuery())
                .map(ProductOutput::from);
    }
}
