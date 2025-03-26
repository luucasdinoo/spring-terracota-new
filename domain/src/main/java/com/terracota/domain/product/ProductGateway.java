package com.terracota.domain.product;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanID;

import java.util.Optional;

public interface ProductGateway {

    Product create(Product product);

    Optional<Product> findById(ProductID productId, CraftsmanID craftsmanId);

    Pagination<Product> listByCraftsman(Craftsman craftsman, SearchQuery aQuery);

    Product update(Product product);

    void deleteById(ProductID anId);
}
