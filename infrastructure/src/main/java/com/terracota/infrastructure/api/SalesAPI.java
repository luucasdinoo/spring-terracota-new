package com.terracota.infrastructure.api;

import com.terracota.domain.sales.SaleItem;
import com.terracota.infrastructure.sales.models.CreateSaleRequest;
import com.terracota.infrastructure.sales.models.GenerateLinkRequest;
import com.terracota.infrastructure.sales.models.GenerateLinkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping("sales")
public interface SalesAPI {

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
//    @Operation(summary = "Create new sale")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Sale created successfully")
//    })
    ResponseEntity<?> create(@RequestBody CreateSaleRequest request);

    @PostMapping("link")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @Operation(summary = "Generate Payment Link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sale created successfully")
    })
    ResponseEntity<GenerateLinkResponse> generatePaymentLink(@RequestBody GenerateLinkRequest request);
}
