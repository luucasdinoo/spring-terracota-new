package com.terracota.application.craftsman.retrieve.list;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.craftsman.CraftsmanGateway;

import java.util.Objects;

public class DefaultListCraftsmenUseCase extends ListCraftsmenUseCase{

    private final CraftsmanGateway craftsmanGateway;

    public DefaultListCraftsmenUseCase(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public Pagination<ListCraftsmenOutput> execute(final SearchQuery aQuery) {
        return this.craftsmanGateway.list(aQuery)
                .map(ListCraftsmenOutput::from);
    }
}
