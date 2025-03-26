package com.terracota.domain.product;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ProductGateway {

    Product create(Product product);

    Optional<Product> findById(ProductID anId);

    Pagination<Product> list(SearchQuery aQuery);

    Product update(Product product);

    void deleteById(ProductID anId);
}
