package com.terracota.infrastructure.api.controller;

import com.terracota.application.product.create.CreateProductCommand;
import com.terracota.application.product.create.CreateProductOutput;
import com.terracota.application.product.create.CreateProductUseCase;
import com.terracota.application.product.delete.DeleteProductUseCase;
import com.terracota.application.product.retrieve.get.GetProductByIdUseCase;
import com.terracota.application.product.retrieve.get.GetProductCommand;
import com.terracota.application.product.update.UpdateProductCommand;
import com.terracota.application.product.update.UpdateProductOutput;
import com.terracota.application.product.update.UpdateProductUseCase;
import com.terracota.infrastructure.api.ProductAPI;
import com.terracota.infrastructure.product.models.CreateProductRequest;
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

    public ProductController(
            final CreateProductUseCase createProductUseCase,
            final GetProductByIdUseCase getProductByIdUseCase,
            final DeleteProductUseCase deleteProductUseCase,
            final UpdateProductUseCase updateProductUseCase
    ) {
        this.createProductUseCase = Objects.requireNonNull(createProductUseCase);
        this.getProductByIdUseCase = Objects.requireNonNull(getProductByIdUseCase);
        this.deleteProductUseCase = Objects.requireNonNull(deleteProductUseCase);
        this.updateProductUseCase = Objects.requireNonNull(updateProductUseCase);
    }

    @Override
    public ResponseEntity<?> create(final CreateProductRequest request) {
        CreateProductCommand aCmd = CreateProductCommand.with(
                request.name(),
                request.description(),
                request.price(),
                request.type(),
                request.craftsmanId()
        );

        CreateProductOutput output = this.createProductUseCase.execute(aCmd);
        return ResponseEntity.created(URI.create("/products/" + output.id())).body(output);
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
}
