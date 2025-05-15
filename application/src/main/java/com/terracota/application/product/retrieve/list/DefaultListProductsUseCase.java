package com.terracota.application.product.retrieve.list;

import com.terracota.application.product.retrieve.get.ProductOutput;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.product.ProductGateway;

import java.util.Objects;

public class DefaultListProductsUseCase extends ListProductsUseCase{

    private final ProductGateway productGateway;

    public DefaultListProductsUseCase(final ProductGateway productGateway
    ) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public Pagination<ProductOutput> execute(SearchQuery input) {
        return this.productGateway.list(input)
                .map(ProductOutput::from);
    }
}
