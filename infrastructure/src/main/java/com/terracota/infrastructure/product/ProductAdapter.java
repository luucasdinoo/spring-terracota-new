package com.terracota.infrastructure.product;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.infrastructure.product.persistence.ProductModel;
import com.terracota.infrastructure.product.persistence.ProductRepository;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class ProductAdapter implements ProductGateway {

    private final ProductRepository productRepository;

    public ProductAdapter(final ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Product create(final Product product) {
        return save(product);
    }

    @Override
    public Optional<Product> findById(final ProductID productId, final CraftsmanID craftsmanId) {
        return this.productRepository.findProductById(productId.getValue() ,craftsmanId.getValue())
                .map(ProductModel::toDomain);
    }

    @Override
    public Pagination<Product> listByCraftsman(final Craftsman craftsman, final SearchQuery aQuery) {
        PageRequest page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        CraftsmanModel craftsmanModel = CraftsmanModel.from(craftsman);
        Page<ProductModel> pageResult = this.productRepository.findProductsByCraftsman(craftsmanModel, page);

        return new Pagination<>(
            pageResult.getNumber(),
            pageResult.getSize(),
            pageResult.getTotalElements(),
            pageResult.map(ProductModel::toDomain).toList()
        );
    }

    @Override
    public Product update(final Product product) {
        return save(product);
    }

    @Override
    public void deleteById(final ProductID anId) {
        if (!productRepository.existsById(anId.getValue())){
            throw EntityNotFoundException.with(Product.class, anId);
        }
        this.productRepository.deleteById(anId.getValue());
    }

    private Product save(final Product product) {
        return this.productRepository.save(ProductModel.from(product))
                .toDomain();
    }
}
