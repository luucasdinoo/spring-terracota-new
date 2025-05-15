package com.terracota.infrastructure.api.controller;

import com.terracota.application.product.create.CreateProductCommand;
import com.terracota.application.product.create.CreateProductOutput;
import com.terracota.application.product.create.CreateProductUseCase;
import com.terracota.application.product.delete.DeleteProductUseCase;
import com.terracota.application.product.management.add.AddProductCommand;
import com.terracota.application.product.management.add.AddProductUseCase;
import com.terracota.application.product.management.remove.RemoveProductCommand;
import com.terracota.application.product.management.remove.RemoveProductUseCase;
import com.terracota.application.product.retrieve.get.GetProductByIdUseCase;
import com.terracota.application.product.retrieve.get.GetProductCommand;
import com.terracota.application.product.retrieve.list.ListByCraftsmanCommand;
import com.terracota.application.product.retrieve.list.ListByCraftsmanUseCase;
import com.terracota.application.product.retrieve.list.ListProductsUseCase;
import com.terracota.application.product.update.UpdateProductCommand;
import com.terracota.application.product.update.UpdateProductOutput;
import com.terracota.application.product.update.UpdateProductUseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.infrastructure.api.ProductAPI;
import com.terracota.infrastructure.product.models.CreateProductRequest;
import com.terracota.infrastructure.product.models.ListProductResponse;
import com.terracota.infrastructure.product.models.ProductResponse;
import com.terracota.infrastructure.product.models.UpdateProductRequest;
import com.terracota.infrastructure.product.presenter.ProductPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ListByCraftsmanUseCase listByCraftsmanUseCase;
    private final ListProductsUseCase listProductsUseCase;
    private final AddProductUseCase addProductUseCase;
    private final RemoveProductUseCase removeProductUseCase;

    public ProductController(
            final CreateProductUseCase createProductUseCase,
            final GetProductByIdUseCase getProductByIdUseCase,
            final DeleteProductUseCase deleteProductUseCase,
            final UpdateProductUseCase updateProductUseCase,
            final ListByCraftsmanUseCase listByCraftsmanUseCase,
            final ListProductsUseCase listProductsUseCase,
            final AddProductUseCase addProductUseCase,
            final RemoveProductUseCase removeProductUseCase
    ) {
        this.createProductUseCase = Objects.requireNonNull(createProductUseCase);
        this.getProductByIdUseCase = Objects.requireNonNull(getProductByIdUseCase);
        this.deleteProductUseCase = Objects.requireNonNull(deleteProductUseCase);
        this.updateProductUseCase = Objects.requireNonNull(updateProductUseCase);
        this.listByCraftsmanUseCase = Objects.requireNonNull(listByCraftsmanUseCase);
        this.listProductsUseCase = Objects.requireNonNull(listProductsUseCase);
        this.addProductUseCase = Objects.requireNonNull(addProductUseCase);
        this.removeProductUseCase = Objects.requireNonNull(removeProductUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateProductRequest request) {
        CreateProductCommand aCmd = CreateProductCommand.with(
                request.name(),
                request.description(),
                request.price(),
                request.quantity(),
                request.type(),
                request.craftsmanId()
        );

        CreateProductOutput output = this.createProductUseCase.execute(aCmd);
        return ResponseEntity.created(URI.create("/products/" + output.productId())).body(output);
    }

    @Override
    public ProductResponse getById(final String productId, final String craftsmanId) {
        GetProductCommand aCmd = GetProductCommand.with(productId, craftsmanId);
        return ProductPresenter.present(this.getProductByIdUseCase.execute(aCmd));
    }

    @Override
    public void deleteById(final String id) {
        this.deleteProductUseCase.execute(id);
    }

    @Override
    public ResponseEntity<?> updateById(final String productId, final String craftsmanId, final UpdateProductRequest request) {
        UpdateProductCommand aCmd = UpdateProductCommand.with(
                productId,
                craftsmanId,
                request.name(),
                request.description(),
                request.price()
        );

        UpdateProductOutput output = this.updateProductUseCase.execute(aCmd);
        return ResponseEntity.ok(output);
    }

    @Override
    public Pagination<ListProductResponse> listByCraftsman(
            final String craftsmanId,
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String dir
    ) {
        ListByCraftsmanCommand aCmd = ListByCraftsmanCommand.with(
                craftsmanId,
                new SearchQuery(page, perPage, search, sort, dir)
        );

        return this.listByCraftsmanUseCase.execute(aCmd)
                .map(ProductPresenter::presentList);
    }

    @Override
    public Pagination<ListProductResponse> list(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String dir
    ) {
        SearchQuery aQuery = new SearchQuery(page, perPage, search, sort, dir);
        return this.listProductsUseCase.execute(aQuery)
                .map(ProductPresenter::presentList);
    }

    @Override
    public void addProduct(final int qtd, final String productId, final String craftsmanId) {
        AddProductCommand aCmd = AddProductCommand.with(productId, craftsmanId, qtd);
        this.addProductUseCase.execute(aCmd);
    }

    @Override
    public void removeProduct(final int qtd, final String productId, final String craftsmanId) {
        RemoveProductCommand aCmd = RemoveProductCommand.with(productId, craftsmanId, qtd);
        this.removeProductUseCase.execute(aCmd);
    }
}
