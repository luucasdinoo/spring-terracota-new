package com.terracota.application.customer.retrieve.list;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.customer.CustomerGateway;

import java.util.Objects;

public class DefaultListCustomerUseCase extends ListCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Pagination<ListCustomerOutput> execute(final SearchQuery aQuery) {
        return this.customerGateway.list(aQuery)
                .map(ListCustomerOutput::from);
    }
}
