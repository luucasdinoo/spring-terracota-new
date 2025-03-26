package com.terracota.infrastructure.user.craftsman;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CraftsmanAdapter implements CraftsmanGateway {

    private final CraftsmanRepository craftsmanRepository;

    public CraftsmanAdapter(final CraftsmanRepository craftsmanRepository) {
        this.craftsmanRepository = Objects.requireNonNull(craftsmanRepository);
    }

    @Override
    public Craftsman create(final Craftsman aCraftsman) {
        return save(aCraftsman);
    }

    @Override
    public void deleteById(final CraftsmanID anId) {
        if (!this.craftsmanRepository.existsById(anId.getValue())) {
            throw EntityNotFoundException.with(Craftsman.class, anId);
        }
        this.craftsmanRepository.deleteById(anId.getValue());
    }

    @Override
    public Optional<Craftsman> findById(final CraftsmanID anId) {
        return this.craftsmanRepository.findById(anId.getValue())
                .map(CraftsmanModel::toDomain);
    }

    @Override
    public Craftsman update(final Craftsman aCraftsman) {
        return save(aCraftsman);
    }

    @Override
    public Pagination<Craftsman> list(final SearchQuery aQuery) {
        PageRequest page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        Page<CraftsmanModel> pageResult = this.craftsmanRepository.findAll(page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CraftsmanModel::toDomain).toList()
        );
    }

    private Craftsman save(final Craftsman aCraftsman) {
        return this.craftsmanRepository.save(CraftsmanModel.from(aCraftsman))
                .toDomain();
    }
}
