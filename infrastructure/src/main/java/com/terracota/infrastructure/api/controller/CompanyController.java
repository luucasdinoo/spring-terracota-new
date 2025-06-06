package com.terracota.infrastructure.api.controller;

import com.terracota.application.company.create.CreateCompanyCommand;
import com.terracota.application.company.create.CreateCompanyOutput;
import com.terracota.application.company.create.CreateCompanyUseCase;
import com.terracota.application.company.management.add.AddCraftsmanCommand;
import com.terracota.application.company.management.add.AddCraftsmanUseCase;
import com.terracota.application.company.management.remove.RemoveCraftsmanCommand;
import com.terracota.application.company.management.remove.RemoveCraftsmanUseCase;
import com.terracota.application.company.retrieve.get.GetCompanyByIdUseCase;
import com.terracota.infrastructure.api.CompanyAPI;
import com.terracota.infrastructure.user.company.models.CompanyResponse;
import com.terracota.infrastructure.user.company.models.CreateCompanyRequest;
import com.terracota.infrastructure.user.company.presenter.CompanyPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class CompanyController implements CompanyAPI {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final AddCraftsmanUseCase addCraftsmanUseCase;
    private final RemoveCraftsmanUseCase removeCraftsmanUseCase;

    public CompanyController(
            final CreateCompanyUseCase createCompanyUseCase,
            final GetCompanyByIdUseCase getCompanyByIdUseCase,
            final AddCraftsmanUseCase addCraftsmanUseCase,
            final RemoveCraftsmanUseCase removeCraftsmanUseCase
    ) {
        this.createCompanyUseCase = Objects.requireNonNull(createCompanyUseCase);
        this.getCompanyByIdUseCase = Objects.requireNonNull(getCompanyByIdUseCase);
        this.addCraftsmanUseCase = Objects.requireNonNull(addCraftsmanUseCase);
        this.removeCraftsmanUseCase = Objects.requireNonNull(removeCraftsmanUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateCompanyRequest request) {
        CreateCompanyCommand aCmd = CreateCompanyCommand.with(
                request.email(),
                request.password(),
                request.role(),
                request.legalName(),
                request.tradeName(),
                request.cnpj(),
                request.phone(),
                request.active()
        );
        CreateCompanyOutput output = this.createCompanyUseCase.execute(aCmd);

        return ResponseEntity.created(URI.create("/companies/" + output.id())).body(output);
    }

    @Override
    public CompanyResponse getById(final String id) {
        return CompanyPresenter.present(this.getCompanyByIdUseCase.execute(id));
    }

    @Override
    public void addCraftsmanToCompany(final String companyId, final String craftsmanId) {
        this.addCraftsmanUseCase.execute(AddCraftsmanCommand.with(companyId, craftsmanId));
    }

    @Override
    public void removeCraftsmanToCompany(final String companyId, final String craftsmanId) {
        this.removeCraftsmanUseCase.execute(RemoveCraftsmanCommand.with(companyId, craftsmanId));
    }
}
