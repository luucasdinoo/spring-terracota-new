package com.terracota.infrastructure.product.persistence;

import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.product.ProductType;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Entity(name = "Product")
@Table(name = "products")
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "craftsman_id", nullable = false)
    private CraftsmanModel craftsman;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public ProductModel(){}

    private ProductModel(
        final String id,
        final String name,
        final String description,
        final BigDecimal price,
        final int quantity,
        final ProductType type,
        final ProductPhotoModel photo,
        final CraftsmanModel craftsman,
        final Instant createdAt,
        final Instant updatedAt
    ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.photo = photo;
        this.craftsman = craftsman;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductPhotoModel getPhoto() {
        return photo;
    }

    public void setPhoto(ProductPhotoModel photo) {
        this.photo = photo;
    }

    public CraftsmanModel getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(CraftsmanModel craftsman) {
        this.craftsman = craftsman;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
