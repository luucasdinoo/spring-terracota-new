package com.terracota.infrastructure.api.controller;

import com.terracota.application.sales.create.CreateSaleCommand;
import com.terracota.application.sales.create.CreateSaleOutput;
import com.terracota.application.sales.create.CreateSaleUseCase;
import com.terracota.infrastructure.api.SalesAPI;
import com.terracota.infrastructure.sales.models.CreateSaleRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
@Tag(name = "Sales")
public class SalesController implements SalesAPI {

    private final CreateSaleUseCase createSaleUseCase;

    public SalesController(final CreateSaleUseCase createSaleUseCase) {
        this.createSaleUseCase = Objects.requireNonNull(createSaleUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateSaleRequest request) {
        CreateSaleCommand aCmd = CreateSaleCommand.with(
                request.orderId(),
                request.craftsmanId(),
                request.customerId(),
                request.productsIds(),
                request.total(),
                request.paymentMethod(),
                request.nsu(),
                request.aut()
        );
        CreateSaleOutput output = this.createSaleUseCase.execute(aCmd);
        return ResponseEntity.created(URI.create("/sales/" + output.orderId())).body(output);
    }
}
