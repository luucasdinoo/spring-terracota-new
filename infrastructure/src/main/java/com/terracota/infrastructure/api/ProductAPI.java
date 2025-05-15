package com.terracota.infrastructure.api;

import com.terracota.domain.pagination.Pagination;
import com.terracota.infrastructure.product.models.CreateProductRequest;
import com.terracota.infrastructure.product.models.ListProductResponse;
import com.terracota.infrastructure.product.models.ProductResponse;
import com.terracota.infrastructure.product.models.UpdateProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("products")
@Tag(name = "Product")
public interface ProductAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN')")
    @Operation(summary = "Create new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully")
    })
    ResponseEntity<?> create(@RequestBody CreateProductRequest request);

    @GetMapping(value = "{productId}/craftsmen/{craftsmanId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve Product by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully")
    })
    ProductResponse getById(@PathVariable String productId, @PathVariable String craftsmanId);

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted")
    })
    void deleteById(@PathVariable String id);

    @PutMapping(
            value = "{productId}/craftsmen/{craftsmanId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN')")
    @Operation(summary = "Update Product by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully")
    })
    ResponseEntity<?> updateById(
            @PathVariable String productId,
            @PathVariable String craftsmanId,
            @RequestBody UpdateProductRequest request
    );

    @GetMapping(value = "craftsmen/{craftsmanId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List craftsman products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Craftsman products listed successfully")
    })
    Pagination<ListProductResponse> listByCraftsman(
            @PathVariable(name = "craftsmanId") final String craftsmanId,
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product listed successfully")
    })
    Pagination<ListProductResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );

    @PatchMapping("{productId}/craftsmen/{craftsmanId}/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Add product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product added")
    })
    void addProduct(
            @RequestParam(name = "qtd", required = false, defaultValue = "0") int qtd,
            @PathVariable String productId,
            @PathVariable String craftsmanId
    );

    @PatchMapping("{productId}/craftsmen/{craftsmanId}/remove")
    @PreAuthorize("hasAnyRole('ADMIN', 'CRAFTSMAN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product removed")
    })
    void removeProduct(
            @RequestParam(name = "qtd", required = false, defaultValue = "0") int qtd,
            @PathVariable String productId,
            @PathVariable String craftsmanId
    );
}