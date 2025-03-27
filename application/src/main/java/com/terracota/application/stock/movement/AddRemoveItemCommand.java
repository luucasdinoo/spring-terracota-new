package com.terracota.application.stock.movement;

public record AddRemoveItemCommand(
        String stockId,
        String productId,
        Integer quantity,
        String operation
) {
}
