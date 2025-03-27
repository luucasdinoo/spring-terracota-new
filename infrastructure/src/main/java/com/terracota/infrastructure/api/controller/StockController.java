package com.terracota.infrastructure.api.controller;

import com.terracota.application.stock.movement.AddRemoveItemCommand;
import com.terracota.application.stock.movement.AddRemoveItemUseCase;
import com.terracota.infrastructure.api.StockAPI;
import com.terracota.infrastructure.stock.models.AddRemoveItemRequest;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class StockController implements StockAPI {

    private final AddRemoveItemUseCase addRemoveItemUseCase;

    public StockController(final AddRemoveItemUseCase addRemoveItemUseCase) {
        this.addRemoveItemUseCase = Objects.requireNonNull(addRemoveItemUseCase);
    }

    @Override
    public void movement(final AddRemoveItemRequest request) {
        AddRemoveItemCommand aCmd = AddRemoveItemCommand.with(
                request.stockId(),
                request.productId(),
                request.quantity(),
                request.operation()
        );

        this.addRemoveItemUseCase.execute(aCmd);
    }
}
