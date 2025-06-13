package com.terracota.infrastructure.api;

import com.terracota.domain.pagination.Pagination;
import com.terracota.infrastructure.user.company.models.CompanyResponse;
import com.terracota.infrastructure.user.company.models.CreateCompanyRequest;
import com.terracota.infrastructure.user.company.models.UpdateCompanyRequest;
import com.terracota.infrastructure.user.craftsman.models.ListCraftsmenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("companies")
@Tag(name = "Company")
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

    @GetMapping(value = "{companyId}/craftsmen", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @Operation(summary = "List craftsmen companies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Craftsmen company listed successfully")
    })
    Pagination<ListCraftsmenResponse> list(
            @PathVariable String companyId,
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
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

    @DeleteMapping(value = "craftsmen/{craftsmanId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'COMPANY')")
    @Operation(summary = "Remove craftsman to company")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Craftsman removed")
    })
    void removeCraftsmanToCompany(
            @PathVariable String craftsmanId
    );
}
