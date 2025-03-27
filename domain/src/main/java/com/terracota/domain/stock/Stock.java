package com.terracota.domain.stock;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.user.craftsman.Craftsman;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stock extends AggregateRoot<StockID> {

    private Craftsman craftsman;

    private List<StockItem> items;

    private Instant createdAt;

    private Instant updatedAt;

    private Stock(
            final StockID stockID,
            final Craftsman craftsman,
            final List<StockItem> items,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(stockID);
        this.craftsman = craftsman;
        this.items = items;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Stock newStock(final Craftsman craftsman) {
        StockID anId = StockID.unique();
        Instant now = Instant.now();
        return new Stock(anId, craftsman, new ArrayList<>(), now, now);
    }

    public static Stock with(
            final StockID stockID,
            final Craftsman craftsman,
            final List<StockItem> items,
            final Instant createdAt,
            final Instant updatedAt
    ){
        return new Stock(stockID, craftsman, items, createdAt, updatedAt);
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public List<StockItem> getItems() {
        return items != null ? Collections.unmodifiableList(items): Collections.emptyList();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void addItem(final StockItem item){
        this.items.add(item);
        this.updatedAt = Instant.now();
    }

    public void removeItem(final StockItem item){
        this.items.remove(item);
        this.updatedAt = Instant.now();
    }
}
