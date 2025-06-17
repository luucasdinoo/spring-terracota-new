package com.terracota.infrastructure.api.controller;

import com.terracota.application.sales.create.CreateSaleCommand;
import com.terracota.application.sales.create.CreateSaleOutput;
import com.terracota.application.sales.create.CreateSaleUseCase;
import com.terracota.application.sales.link.GenerateLinkUseCase;
import com.terracota.infrastructure.api.SalesAPI;
import com.terracota.infrastructure.sales.models.CreateSaleRequest;
import com.terracota.infrastructure.sales.models.GenerateLinkRequest;
import com.terracota.infrastructure.sales.models.GenerateLinkResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Tag(name = "Sales")
public class SalesController implements SalesAPI {

    private final CreateSaleUseCase createSaleUseCase;
    private final GenerateLinkUseCase generateLinkUseCase;

    public SalesController(
            final CreateSaleUseCase createSaleUseCase,
            final GenerateLinkUseCase generateLinkUseCase
    ) {
        this.createSaleUseCase = Objects.requireNonNull(createSaleUseCase);
        this.generateLinkUseCase = Objects.requireNonNull(generateLinkUseCase);
    }

    @Override
    public ResponseEntity<CreateSaleOutput> create(final CreateSaleRequest request) {
        CreateSaleCommand aCmd = CreateSaleCommand.with(
                request.preferenceId(),
                request.paymentId(),
                request.customerId(),
                request.productsIds(),
                request.total(),
                request.paymentMethod(),
                request.status()
        );
        CreateSaleOutput output = this.createSaleUseCase.execute(aCmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @Override
    public ResponseEntity<GenerateLinkResponse> generatePaymentLink(final GenerateLinkRequest items) {
        String link = this.generateLinkUseCase.execute(items.items());
        return ResponseEntity.ok(new GenerateLinkResponse(link));
    }
}
