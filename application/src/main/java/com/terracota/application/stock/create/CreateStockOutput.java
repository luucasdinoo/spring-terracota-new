package com.terracota.application.stock.create;

import com.terracota.domain.stock.Stock;

public record CreateStockOutput(String id) {
    public static CreateStockOutput from(final Stock stock){
        return new CreateStockOutput(stock.getId().getValue());
    }
}
