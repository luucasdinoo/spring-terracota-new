package com.terracota.infrastructure.api.controller;

import com.terracota.application.company.create.CreateCompanyCommand;
import com.terracota.application.company.create.CreateCompanyOutput;
import com.terracota.application.company.create.CreateCompanyUseCase;
import com.terracota.application.company.management.add.AddCraftsmanCommand;
import com.terracota.application.company.management.add.AddCraftsmanUseCase;
import com.terracota.application.company.management.remove.RemoveCraftsmanCommand;
import com.terracota.application.company.management.remove.RemoveCraftsmanUseCase;
import com.terracota.application.company.retrieve.get.GetCompanyByIdUseCase;
import com.terracota.application.company.retrieve.list.ListCompaniesUseCase;
import com.terracota.application.company.update.UpdateCompanyCommand;
import com.terracota.application.company.update.UpdateCompanyOutput;
import com.terracota.application.company.update.UpdateCompanyUseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.infrastructure.api.CompanyAPI;
import com.terracota.infrastructure.user.company.models.CompanyResponse;
import com.terracota.infrastructure.user.company.models.CreateCompanyRequest;
import com.terracota.infrastructure.user.company.models.ListCompaniesResponse;
import com.terracota.infrastructure.user.company.models.UpdateCompanyRequest;
import com.terracota.infrastructure.user.company.presenter.CompanyPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class CompanyController implements CompanyAPI {

    private final CreateCompanyUseCase createCompanyUseCase;
    private final GetCompanyByIdUseCase getCompanyByIdUseCase;
    private final ListCompaniesUseCase listCompaniesUseCase;
    private final UpdateCompanyUseCase updateCompanyUseCase;
    private final AddCraftsmanUseCase addCraftsmanUseCase;
    private final RemoveCraftsmanUseCase removeCraftsmanUseCase;

    public CompanyController(
            final CreateCompanyUseCase createCompanyUseCase,
            final GetCompanyByIdUseCase getCompanyByIdUseCase,
            final ListCompaniesUseCase listCompaniesUseCase,
            final UpdateCompanyUseCase updateCompanyUseCase,
            final AddCraftsmanUseCase addCraftsmanUseCase,
            final RemoveCraftsmanUseCase removeCraftsmanUseCase
    ) {
        this.createCompanyUseCase = Objects.requireNonNull(createCompanyUseCase);
        this.getCompanyByIdUseCase = Objects.requireNonNull(getCompanyByIdUseCase);
        this.listCompaniesUseCase = Objects.requireNonNull(listCompaniesUseCase);
        this.updateCompanyUseCase = Objects.requireNonNull(updateCompanyUseCase);
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
    public ResponseEntity<?> updateById(final String id, final UpdateCompanyRequest request) {
        UpdateCompanyCommand aCmd = UpdateCompanyCommand.with(id, request.legalName(), request.tradeName(), request.phone(), request.isActive());
        this.updateCompanyUseCase.execute(aCmd);
        return ResponseEntity.noContent().build();
    }

    @Override
    public CompanyResponse getById(final String id) {
        return CompanyPresenter.present(this.getCompanyByIdUseCase.execute(id));
    }

    @Override
    public Pagination<ListCompaniesResponse> list(final String search, int page, int perPage, String sort, final String dir) {
        return this.listCompaniesUseCase.execute(new SearchQuery(page, perPage, search, sort, dir))
                .map(CompanyPresenter::present);
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
