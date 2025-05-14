package com.terracota.infrastructure.api.controller;

import com.terracota.application.cart.management.add.AddItemCommand;
import com.terracota.application.cart.management.add.AddItemUseCase;
import com.terracota.application.cart.management.remove.RemoveItemCommand;
import com.terracota.application.cart.management.remove.RemoveItemUseCase;
import com.terracota.infrastructure.api.CartAPI;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CartController implements CartAPI {

    private final AddItemUseCase addItemUseCase;
    private final RemoveItemUseCase removeItemUseCase;

    public CartController(
            final AddItemUseCase addItemUseCase,
            final RemoveItemUseCase removeItemUseCase
    ) {
        this.addItemUseCase = Objects.requireNonNull(addItemUseCase);
        this.removeItemUseCase = Objects.requireNonNull(removeItemUseCase);
    }

    @Override
    public void addCartItem(
            final String productId,
            final String craftsmanId,
            final String customerId,
            final Integer qtd
    ) {
        AddItemCommand aCmd = AddItemCommand.with(productId, craftsmanId, customerId, qtd);
        this.addItemUseCase.execute(aCmd);
    }

    @Override
    public void removeCartItem(
            final String productId,
            final String craftsmanId,
            final String customerId,
            final Integer qtd
    ) {
        RemoveItemCommand aCmd = RemoveItemCommand.with(productId, craftsmanId, customerId, qtd);
        this.removeItemUseCase.execute(aCmd);
    }
}
