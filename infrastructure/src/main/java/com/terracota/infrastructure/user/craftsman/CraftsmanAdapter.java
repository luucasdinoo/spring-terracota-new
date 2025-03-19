package com.terracota.infrastructure.user.craftsman;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanRepository;

import java.util.Objects;
import java.util.Optional;

public class CraftsmanAdapter implements CraftsmanGateway {

    private final CraftsmanRepository craftsmanRepository;

    public CraftsmanAdapter(final CraftsmanRepository craftsmanRepository) {
        this.craftsmanRepository = Objects.requireNonNull(craftsmanRepository);
    }

    @Override
    public Craftsman create(Craftsman aCraftsman) {
        return null;
    }

    @Override
    public void deleteById(CraftsmanID anId) {

    }

    @Override
    public Optional<Craftsman> findById(CraftsmanID anId) {
        return Optional.empty();
    }

    @Override
    public Craftsman update(Craftsman aCraftsman) {
        return null;
    }

    @Override
    public Pagination<Craftsman> findAll(SearchQuery aQuery) {
        return null;
    }
}
