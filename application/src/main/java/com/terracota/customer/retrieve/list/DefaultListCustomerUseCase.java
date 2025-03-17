package com.terracota.customer.retrieve.list;

import com.terracota.customer.CustomerGateway;
import com.terracota.pagination.Pagination;
import com.terracota.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCustomerUseCase extends ListCustomerUseCase{

    private final CustomerGateway customerGateway;

    public DefaultListCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public Pagination<ListCustomerOutput> execute(final SearchQuery aQuery) {
        return this.customerGateway.findAll(aQuery)
                .map(ListCustomerOutput::from);
    }
}
