package com.terracota.infrastructure.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("carts")
public interface CartAPI {

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Add item to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item added to cart")
    })
    void addCartItem(
            @RequestParam(name = "productId") String productId,
            @RequestParam(name = "craftsmanId") String craftsmanId,
            @RequestParam(name = "customerId") String customerId,
            @RequestParam(name = "qtd", defaultValue = "1", required = false) Integer qtd
    );

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove item to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removed to cart")
    })
    void removeCartItem(
            @RequestParam(name = "productId") String productId,
            @RequestParam(name = "craftsmanId") String craftsmanId,
            @RequestParam(name = "customerId") String customerId,
            @RequestParam(name = "qtd", defaultValue = "1", required = false) Integer qtd
    );
}
