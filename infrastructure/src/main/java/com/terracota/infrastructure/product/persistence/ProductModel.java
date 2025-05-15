package com.terracota.infrastructure.product.persistence;

import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.product.ProductType;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Entity(name = "Product")
@Table(name = "products")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductModel {

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private ProductPhotoModel photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "craftsman_id", nullable = false)
    private CraftsmanModel craftsman;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public static ProductModel from(final Product product){
        return new ProductModel(
                product.getId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getType(),
                product.getPhoto()
                        .map(ProductPhotoModel::from)
                        .orElse(null),
                CraftsmanModel.from(product.getCraftsman()),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public Product toDomain(){
        return Product.with(
                ProductID.from(getId()),
                getName(),
                getDescription(),
                getPrice(),
                getQuantity(),
                getType(),
                Optional.ofNullable(getPhoto())
                        .map(ProductPhotoModel::toDomain)
                        .orElse(null),
                getCraftsman().toDomain(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }
}
