package com.terracota.infrastructure.api;

import com.terracota.domain.pagination.Pagination;
import com.terracota.infrastructure.user.company.models.CompanyResponse;
import com.terracota.infrastructure.user.company.models.CreateCompanyRequest;
import com.terracota.infrastructure.user.company.models.ListCompaniesResponse;
import com.terracota.infrastructure.user.company.models.UpdateCompanyRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @Operation(summary = "Update Company by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Company updated")
    })
    ResponseEntity<?> updateById(
            @PathVariable String id,
            @RequestBody UpdateCompanyRequest request
    );

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve company by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Company retrieved successfully")
    })
    CompanyResponse getById(@PathVariable String id);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comapnoes listed successfully")
    })
    Pagination<ListCompaniesResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "legalName") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );

    @PostMapping(value = "{companyId}/craftsmen/{craftsmanId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @Operation(summary = "Add craftsman to company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Craftsman added")
    })
    void addCraftsmanToCompany(
            @PathVariable String companyId,
            @PathVariable String craftsmanId
    );

    @DeleteMapping(value = "{companyId}/craftsmen/{craftsmanId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @Operation(summary = "Remove craftsman to company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Craftsman removed")
    })
    void removeCraftsmanToCompany(
            @PathVariable String companyId,
            @PathVariable String craftsmanId
    );
}
