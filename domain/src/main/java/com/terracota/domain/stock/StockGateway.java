package com.terracota.domain.stock;

import java.util.Optional;

public interface StockGateway {

    Stock save(Stock stock);

    Optional<Stock> findById(StockID id);
}
