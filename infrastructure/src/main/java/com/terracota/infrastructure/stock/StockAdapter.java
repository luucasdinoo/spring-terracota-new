package com.terracota.infrastructure.stock;

import com.terracota.domain.stock.Stock;
import com.terracota.domain.stock.StockGateway;
import com.terracota.domain.stock.StockID;
import com.terracota.infrastructure.stock.persistence.StockModel;
import com.terracota.infrastructure.stock.persistence.StockRepository;

import java.util.Objects;
import java.util.Optional;

public class StockAdapter implements StockGateway {

    private final StockRepository stockRepository;

    public StockAdapter(final StockRepository stockRepository) {
        this.stockRepository = Objects.requireNonNull(stockRepository);
    }

    @Override
    public Stock save(final Stock stock) {
        return this.stockRepository.save(StockModel.from(stock))
                .toDomain();
    }

    @Override
    public Optional<Stock> findById(final StockID id) {
        return this.stockRepository.findById(id.getValue())
                .map(StockModel::toDomain);
    }
}
