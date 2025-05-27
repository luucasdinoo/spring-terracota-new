package com.terracota.infrastructure.api.controller;

import com.terracota.application.customer.create.CreateCustomerCommand;
import com.terracota.application.customer.create.CreateCustomerOutput;
import com.terracota.application.customer.create.CreateCustomerUseCase;
import com.terracota.application.customer.delete.DeleteCustomerUseCase;
import com.terracota.application.customer.retrieve.get.GetCustomerByEmailUseCase;
import com.terracota.application.customer.retrieve.get.GetCustomerBydIdUseCase;
import com.terracota.application.customer.retrieve.list.ListCustomerUseCase;
import com.terracota.application.customer.update.UpdateCustomerCommand;
import com.terracota.application.customer.update.UpdateCustomerOutput;
import com.terracota.application.customer.update.UpdateCustomerUseCase;
import com.terracota.application.files.UploadImageUseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.resource.Resource;
import com.terracota.infrastructure.api.CustomerAPI;
import com.terracota.infrastructure.user.customer.models.*;
import com.terracota.infrastructure.user.customer.presenter.CustomerPresenter;
import com.terracota.infrastructure.utils.HashingUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
public class CustomerController implements CustomerAPI {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final ListCustomerUseCase listCustomerUseCase;
    private final GetCustomerBydIdUseCase getCustomerBydIdUseCase;
    private final GetCustomerByEmailUseCase getCustomerByEmailUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase,
            final ListCustomerUseCase listCustomerUseCase,
            final GetCustomerBydIdUseCase getCustomerBydIdUseCase,
            final GetCustomerByEmailUseCase getCustomerByEmailUseCase,
            final DeleteCustomerUseCase deleteCustomerUseCase,
            final UpdateCustomerUseCase updateCustomerUseCase,
            final UploadImageUseCase uploadImageUseCase
    ) {
        this.createCustomerUseCase = Objects.requireNonNull(createCustomerUseCase);
        this.listCustomerUseCase = Objects.requireNonNull(listCustomerUseCase);
        this.getCustomerBydIdUseCase = Objects.requireNonNull(getCustomerBydIdUseCase);
        this.getCustomerByEmailUseCase = Objects.requireNonNull(getCustomerByEmailUseCase);
        this.deleteCustomerUseCase = Objects.requireNonNull(deleteCustomerUseCase);
        this.updateCustomerUseCase = Objects.requireNonNull(updateCustomerUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateCustomerRequest request) {
        CreateCustomerCommand aCommand = CreateCustomerCommand.with(
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
        CreateCustomerOutput output = this.createCustomerUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/customers/" + output.id())).body(output);
    }

    @Override
    public Pagination<ListCustomerResponse> list(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String dir
    ) {
        return this.listCustomerUseCase.execute(new SearchQuery(page, perPage, search, sort, dir))
                .map(CustomerPresenter::present);
    }

    @Override
    public CustomerResponse getById(String id) {
        return CustomerPresenter.present(this.getCustomerBydIdUseCase.execute(id));
    }

    @Override
    public CustomerResponse getByEmail(String email) {
        return CustomerPresenter.present(this.getCustomerByEmailUseCase.execute(email));
    }

    @Override
    public void deleteById(final String id) {
        this.deleteCustomerUseCase.execute(id);
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCustomerRequest request) {
        UpdateCustomerCommand aCmd = UpdateCustomerCommand.with(
                id,
                request.name(),
                request.phone(),
                request.isActive()
        );

        UpdateCustomerOutput output = this.updateCustomerUseCase.execute(aCmd);
        return ResponseEntity.ok(output);
    }
}
