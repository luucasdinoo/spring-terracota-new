package com.terracota.infrastructure.api;

import com.terracota.infrastructure.product.models.CreateProductRequest;
import com.terracota.infrastructure.product.models.ProductResponse;
import com.terracota.infrastructure.product.models.UpdateProductRequest;
import com.terracota.infrastructure.user.customer.models.UpdateCustomerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("products")
@Tag(name = "Product")
public interface ProductAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @Operation(summary = "Update Product by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully")
    })
    ResponseEntity<?> updateById(
            @PathVariable String productId,
            @PathVariable String craftsmanId,
            @RequestBody UpdateProductRequest request);
}
