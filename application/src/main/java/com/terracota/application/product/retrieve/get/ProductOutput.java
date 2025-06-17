package com.terracota.application.product.retrieve.get;

import com.terracota.domain.product.Product;
import com.terracota.domain.resource.ImagePhoto;

import java.time.Instant;

public record ProductOutput(
        String id,
        String name,
        String description,
        double price,
        int quantity,
        String type,
        String photo,
        String craftsmanId,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProductOutput from(final Product product){
        return new ProductOutput(
                product.getId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice().doubleValue(),
                product.getQuantity(),
                product.getType().getValue(),
                product.getPhoto()
                        .map(ImagePhoto::location)
                        .orElse(null),
                product.getCraftsman().getId().getValue(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
