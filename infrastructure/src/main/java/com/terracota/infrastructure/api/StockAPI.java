package com.terracota.infrastructure.api;

import com.terracota.infrastructure.stock.models.AddRemoveItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("stocks")
@Tag(name = "Stock")
public interface StockAPI {

    @PostMapping("products")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Add/Remove item from stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removed/added successfully")
    })
    void movement(@RequestBody AddRemoveItemRequest request);
}
