package com.terracota.application.stock.create;

import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockGateway;
import com.terracota.domain.user.craftsman.Craftsman;

import java.util.Objects;

public class DefaultCreateStockUseCase extends CreateStockUseCase{

    private final StockGateway stockGateway;

    public DefaultCreateStockUseCase(final StockGateway stockGateway) {
        this.stockGateway = Objects.requireNonNull(stockGateway);
    }

    @Override
    public CreateStockOutput execute(final Craftsman craftsman) {
        Stock stock = Stock.newStock(craftsman);
        return CreateStockOutput.from(this.stockGateway.save(stock));
    }
}
