package com.terracota.infrastructure.api.controller;

import com.terracota.application.craftsman.create.CreateCraftsmanCommand;
import com.terracota.application.craftsman.create.CreateCraftsmanOutput;
import com.terracota.application.craftsman.create.CreateCraftsmanUseCase;
import com.terracota.application.craftsman.delete.DeleteCraftsmanUseCase;
import com.terracota.application.craftsman.retrieve.get.GetCraftsmanByEmailUseCase;
import com.terracota.application.craftsman.retrieve.get.GetCraftsmanByIdUseCase;
import com.terracota.application.craftsman.retrieve.list.ListCraftsmenUseCase;
import com.terracota.application.craftsman.update.UpdateCraftsmanCommand;
import com.terracota.application.craftsman.update.UpdateCraftsmanOutput;
import com.terracota.application.craftsman.update.UpdateCraftsmanUseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.infrastructure.api.CraftsmanAPI;
import com.terracota.infrastructure.user.craftsman.models.CraftsmanResponse;
import com.terracota.infrastructure.user.craftsman.models.CreateCraftsmanRequest;
import com.terracota.infrastructure.user.craftsman.models.ListCraftsmenResponse;
import com.terracota.infrastructure.user.craftsman.models.UpdateCraftsmanRequest;
import com.terracota.infrastructure.user.craftsman.presenter.CraftsmanPresenter;
import com.terracota.infrastructure.user.customer.models.AddressRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CraftsmanController implements CraftsmanAPI {

    private final CreateCraftsmanUseCase createCraftsmanUseCase;
    private final ListCraftsmenUseCase listCraftsmenUseCase;
    private final GetCraftsmanByIdUseCase getCraftsmanByIdUseCase;
    private final GetCraftsmanByEmailUseCase getCraftsmanByEmailUseCase;
    private final DeleteCraftsmanUseCase deleteCraftsmanUseCase;
    private final UpdateCraftsmanUseCase updateCraftsmanUseCase;

    public CraftsmanController(
            final CreateCraftsmanUseCase createCraftsmanUseCase,
            final ListCraftsmenUseCase listCraftsmenUseCase,
            final GetCraftsmanByIdUseCase getCraftsmanByIdUseCase,
            final GetCraftsmanByEmailUseCase getCraftsmanByEmailUseCase,
            final DeleteCraftsmanUseCase deleteCraftsmanUseCase,
            final UpdateCraftsmanUseCase updateCraftsmanUseCase
    ) {
        this.createCraftsmanUseCase = Objects.requireNonNull(createCraftsmanUseCase);
        this.listCraftsmenUseCase = Objects.requireNonNull(listCraftsmenUseCase);
        this.getCraftsmanByIdUseCase = Objects.requireNonNull(getCraftsmanByIdUseCase);
        this.getCraftsmanByEmailUseCase = Objects.requireNonNull(getCraftsmanByEmailUseCase);
        this.deleteCraftsmanUseCase = Objects.requireNonNull(deleteCraftsmanUseCase);
        this.updateCraftsmanUseCase = Objects.requireNonNull(updateCraftsmanUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateCraftsmanRequest request) {
        CreateCraftsmanCommand aCmd = CreateCraftsmanCommand.with(
                request.email(),
                request.password(),
                request.role(),
                request.name(),
                request.phone(),
                request.isActive(),
                request.cpf(),
                Optional.ofNullable(request.address())
                        .map(AddressRequest::toDomain)
                        .orElse(null)
        );
        CreateCraftsmanOutput output = this.createCraftsmanUseCase.execute(aCmd);

        return ResponseEntity.created(URI.create("/craftsmen/" + output.id())).body(output);
    }

    @Override
    public Pagination<ListCraftsmenResponse> list(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String dir
    ) {
        return this.listCraftsmenUseCase.execute(new SearchQuery(page, perPage, search, sort, dir))
                .map(CraftsmanPresenter::present);
    }

    @Override
    public CraftsmanResponse getById(final String id) {
        return CraftsmanPresenter.present(this.getCraftsmanByIdUseCase.execute(id));
    }

    @Override
    public CraftsmanResponse getByEmail(String email) {
        return CraftsmanPresenter.present(this.getCraftsmanByEmailUseCase.execute(email));
    }

    @Override
    public void deleteById(final String id) {
        this.deleteCraftsmanUseCase.execute(id);
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCraftsmanRequest request) {
        UpdateCraftsmanCommand aCmd = UpdateCraftsmanCommand.with(
                id,
                request.name(),
                request.phone(),
                request.isActive()
        );

        UpdateCraftsmanOutput output = this.updateCraftsmanUseCase.execute(aCmd);
        return ResponseEntity.ok(output);
    }
}
