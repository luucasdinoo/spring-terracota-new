package com.terracota.infrastructure.api;

import com.terracota.infrastructure.user.company.models.CompanyResponse;
import com.terracota.infrastructure.user.company.models.CreateCompanyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("companies")
public interface CompanyAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create new company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Company created successfully")
    })
    ResponseEntity<?> create(
            @RequestBody CreateCompanyRequest request
    );

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve company by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company retrieved successfully")
    })
    CompanyResponse getById(@PathVariable String id);

    @PostMapping(value = "{companyId}/craftsmen/{craftsmanId}")
    @Operation(summary = "Add craftsman to company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Craftsman added")
    })
    void addCraftsmanToCompany(
            @PathVariable String companyId,
            @PathVariable String craftsmanId
    );

    @DeleteMapping(value = "{companyId}/craftsmen/{craftsmanId}")
    @Operation(summary = "Remove craftsman to company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Craftsman removed")
    })
    void removeCraftsmanToCompany(
            @PathVariable String companyId,
            @PathVariable String craftsmanId
    );
}
