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
        // TODO
        return null;
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
