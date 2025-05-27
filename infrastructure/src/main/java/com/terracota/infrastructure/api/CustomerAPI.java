package com.terracota.infrastructure.api;

import com.terracota.domain.pagination.Pagination;
import com.terracota.infrastructure.user.customer.models.CreateCustomerRequest;
import com.terracota.infrastructure.user.customer.models.CustomerResponse;
import com.terracota.infrastructure.user.customer.models.ListCustomerResponse;
import com.terracota.infrastructure.user.customer.models.UpdateCustomerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customers")
@Tag(name = "Customer")
public interface CustomerAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully")
    })
    ResponseEntity<?> create(
            @RequestBody CreateCustomerRequest request
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer listed successfully")
    })
    Pagination<ListCustomerResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve customer by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer retrieved successfully")
    })
    CustomerResponse getById(@PathVariable String id);

    @GetMapping(value = "email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve customer by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer retrieved successfully")
    })
    CustomerResponse getByEmail(@PathVariable String email);

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted")
    })
    void deleteById(@PathVariable String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @Operation(summary = "Update customer by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    })
    ResponseEntity<?> updateById(@PathVariable String id, @RequestBody UpdateCustomerRequest request);
}
