package com.terracota.application.company.retrieve.list;

import com.terracota.application.craftsman.retrieve.list.ListCraftsmenOutput;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.projection.CraftsmenDomainProjection;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;

import java.util.Objects;

public class DefaultListCraftsmenCompanyUseCase extends ListCraftsmenCompanyUseCase {

    private final CompanyGateway companyGateway;

    public DefaultListCraftsmenCompanyUseCase(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Override
    public Pagination<ListCraftsmenOutput> execute(final ListCraftsmenCompanyCommand input) {
        return this.companyGateway.list(input.searchQuery(), CompanyID.from(input.id()))
                .map(this::from);
    }

    private ListCraftsmenOutput from(final CraftsmenDomainProjection projection) {
        return new ListCraftsmenOutput(projection.id(), projection.email(), projection.role());
    }
}
