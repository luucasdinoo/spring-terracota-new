package com.terracota.application.stock;

public record AddRemoveItemCommand(
        String stockId,
        String productId,
        Integer quantity,
        String operation
) {
}
