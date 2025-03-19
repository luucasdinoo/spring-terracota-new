package com.terracota.domain.user.craftsman;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.UserID;

import java.util.Optional;

public interface CraftsmanGateway {

    Craftsman create(Craftsman aCraftsman);

    void deleteById(UserID anId);

    Optional<Craftsman> findById(UserID anId);

    Craftsman update(Craftsman aCraftsman);

    Pagination<Craftsman> findAll(SearchQuery aQuery);

}
