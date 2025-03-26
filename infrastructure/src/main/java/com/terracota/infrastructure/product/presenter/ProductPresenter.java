package com.terracota.infrastructure.product.presenter;

import com.terracota.application.product.retrieve.get.ProductOutput;
import com.terracota.domain.product.ProductType;
import com.terracota.infrastructure.product.models.ProductResponse;

public interface ProductPresenter {

    static ProductResponse present(final ProductOutput output){
        return new ProductResponse(
                output.id(),
                output.name(),
                output.description(),
                output.price(),
                ProductType.valueOf(output.type()),
                output.photo(),
                output.craftsmanId(),
                output.createdAt(),
                output.createdAt()
        );
    }
}
