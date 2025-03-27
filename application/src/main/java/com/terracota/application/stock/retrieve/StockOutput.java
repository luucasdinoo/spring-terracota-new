package com.terracota.application.stock.retrieve;

import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockItem;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

public record StockOutput(
        String craftsmanId,
        List<StockItem> items,
        Instant createdAt,
        Instant updatedAt
) {
    public static StockOutput from(final Stock stock){
        String craftsmanId = stock.getCraftsman().getId().getValue();
        return new StockOutput(
                craftsmanId,
                Collections.unmodifiableList(stock.getItems()),
                stock.getCreatedAt(),
                stock.getUpdatedAt()
        );
    }
}
