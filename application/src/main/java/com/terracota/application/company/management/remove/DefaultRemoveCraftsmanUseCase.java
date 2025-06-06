package com.terracota.application.company.management.remove;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultRemoveCraftsmanUseCase extends RemoveCraftsmanUseCase {

    private final CompanyGateway companyGateway;
    private final CraftsmanGateway craftsmanGateway;

    public DefaultRemoveCraftsmanUseCase(final CompanyGateway companyGateway, final CraftsmanGateway craftsmanGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public void execute(final RemoveCraftsmanCommand input) {
        CompanyID companyId = CompanyID.from(input.companyId());
        Company company = this.companyGateway.findById(companyId)
                .orElseThrow(() -> EntityNotFoundException.with(Company.class, companyId));

        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        Craftsman craftsman = this.craftsmanGateway.findById(craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, craftsmanId));

        company.remove(craftsman);
        this.companyGateway.update(company);
    }
}
