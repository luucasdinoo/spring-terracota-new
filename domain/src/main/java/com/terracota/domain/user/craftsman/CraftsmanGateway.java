package com.terracota.domain.user.craftsman;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CraftsmanGateway {

    Craftsman create(Craftsman aCraftsman);

    void deleteById(CraftsmanID anId);

    Optional<Craftsman> findById(CraftsmanID anId);

    Craftsman update(Craftsman aCraftsman);

    Pagination<Craftsman> list(SearchQuery aQuery);

    String hashPassword(String password);

}
