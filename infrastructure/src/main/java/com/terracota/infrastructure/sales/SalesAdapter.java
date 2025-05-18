package com.terracota.infrastructure.sales;

import com.terracota.domain.sales.Sale;
import com.terracota.domain.sales.SaleID;
import com.terracota.domain.sales.SalesGateway;
import com.terracota.infrastructure.sales.persistence.SaleModel;
import com.terracota.infrastructure.sales.persistence.SalesRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class SalesAdapter implements SalesGateway {

    private final SalesRepository salesRepository;

    public SalesAdapter(final SalesRepository salesRepository) {
        this.salesRepository = Objects.requireNonNull(salesRepository);
    }

    @Override
    public Sale create(final Sale sale) {
        return this.persist(sale).toDomain();
    }

    @Override
    public void save(final Sale sale) {
        this.persist(sale);
    }

    @Override
    public Optional<Sale> findById(final SaleID sale) {
        return Optional.empty();
    }

    private SaleModel persist(final Sale sale) {
        return salesRepository.save(SaleModel.from(sale));
    }
}
