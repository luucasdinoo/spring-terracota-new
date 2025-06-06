package com.terracota.application.company.management.add;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Objects;

public class DefaultAddCraftsmanUseCase extends AddCraftsmanUseCase{

    private final CompanyGateway companyGateway;
    private final CraftsmanGateway craftsmanGateway;

    public DefaultAddCraftsmanUseCase(final CompanyGateway companyGateway, final CraftsmanGateway craftsmanGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Override
    public void execute(final AddCraftsmanCommand input) {
        CompanyID companyId = CompanyID.from(input.companyId());
        Company company = this.companyGateway.findById(companyId)
                .orElseThrow(() -> EntityNotFoundException.with(Company.class, companyId));

        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        Craftsman craftsman = this.craftsmanGateway.findById(craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, craftsmanId));

        company.add(craftsman);
        this.companyGateway.update(company);
    }
}
