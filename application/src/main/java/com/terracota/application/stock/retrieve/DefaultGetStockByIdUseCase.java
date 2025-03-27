package com.terracota.application.stock.retrieve;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockGateway;
import com.terracota.domain.stock.StockID;

import java.util.Objects;

public class DefaultGetStockByIdUseCase extends GetStockByIdUseCase{

    private final StockGateway stockGateway;

    public DefaultGetStockByIdUseCase(final StockGateway stockGateway) {
        this.stockGateway = Objects.requireNonNull(stockGateway);
    }

    @Override
    public StockOutput execute(final String input) {
        StockID anId = StockID.from(input);
        return this.stockGateway.findById(anId)
                .map(StockOutput::from)
                .orElseThrow(() -> EntityNotFoundException.with(Stock.class, anId));
    }
}
