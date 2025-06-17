package com.terracota.infrastructure.api;

import com.terracota.domain.pagination.Pagination;
import com.terracota.infrastructure.user.craftsman.models.CraftsmanResponse;
import com.terracota.infrastructure.user.craftsman.models.CreateCraftsmanRequest;
import com.terracota.infrastructure.user.craftsman.models.ListCraftsmenResponse;
import com.terracota.infrastructure.user.craftsman.models.UpdateCraftsmanRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("craftsmen")
@Tag(name = "Craftsman")
public interface CraftsmanAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create new craftsman")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Craftsman created successfully")
    })
    ResponseEntity<?> create(@RequestBody CreateCraftsmanRequest request);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List craftsmen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Craftsmen listed successfully")
    })
    Pagination<ListCraftsmenResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve craftsman by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Craftsman retrieved successfully")
    })
    CraftsmanResponse getById(@PathVariable String id);

    @GetMapping(value = "email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve craftsman by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer retrieved successfully")
    })
    CraftsmanResponse getByEmail(@PathVariable String email);

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN', 'COMPANY')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete craftsman by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Craftsman deleted")
    })
    void deleteById(@PathVariable String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN')")
    @Operation(summary = "Craftsman customer by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Craftsman updated successfully")
    })
    ResponseEntity<?> updateById(@PathVariable String id, @RequestBody UpdateCraftsmanRequest request);
}
