package com.terracota.application.stock.movement;

public record AddRemoveItemCommand(
        String stockId,
        String productId,
        int quantity,
        String operation
) {
    public static AddRemoveItemCommand with(
            final String stockId,
            final String productId,
            final int quantity,
            String operation
    ){
        return new AddRemoveItemCommand(stockId, productId, quantity, operation);
    }
}
