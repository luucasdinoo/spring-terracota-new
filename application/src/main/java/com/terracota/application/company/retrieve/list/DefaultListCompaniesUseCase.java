package com.terracota.application.company.retrieve.list;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.company.CompanyGateway;

import java.util.Objects;

public class DefaultListCompaniesUseCase extends ListCompaniesUseCase {

    private final CompanyGateway companyGateway;

    public DefaultListCompaniesUseCase(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Override
    public Pagination<ListCompaniesOutput> execute(final SearchQuery aQuery) {
        return this.companyGateway.list(aQuery)
                .map(ListCompaniesOutput::from);
    }
}
