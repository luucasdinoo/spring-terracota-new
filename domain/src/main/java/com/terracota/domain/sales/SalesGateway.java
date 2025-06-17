package com.terracota.domain.sales;

import java.util.Optional;

public interface SalesGateway {

    Sale create(Sale sale);

    void save(Sale sale);

    Optional<Sale> findById(SaleID sale);

}
