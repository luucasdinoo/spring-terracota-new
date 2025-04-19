package com.terracota.domain.product;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.user.craftsman.Craftsman;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public class Product extends AggregateRoot<ProductID> {

    private String name;

    private String description;

    private BigDecimal price;

    private int quantity;

    private ProductType type;

    private ProductPhoto photo;

    private Craftsman craftsman;

    private Instant createdAt;

    private Instant updatedAt;

    private Product(
            final ProductID productID,
            final String name,
            final String description,
            final BigDecimal price,
            final int quantity,
            final ProductType type,
            final ProductPhoto photo,
            final Craftsman craftsman,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(productID);
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

    public static Product newProduct(
            final String name,
            final String description,
            final BigDecimal price,
            final int quantity,
            final ProductType type,
            final ProductPhoto photo,
            final Craftsman craftsman
    ){
        ProductID anId = ProductID.unique();
        Instant now = Instant.now();
        return new Product(anId, name, description, price, quantity, type, photo, craftsman, now, now);
    }

    public static Product with(
            final ProductID anId,
            final String name,
            final String description,
            final BigDecimal price,
            final int quantity,
            final ProductType type,
            final ProductPhoto photo,
            final Craftsman craftsman,
            final Instant createdAt,
            final Instant updatedAt
    ){
        return new Product(anId, name, description, price, quantity, type, photo, craftsman, createdAt, updatedAt);
    }

    public Product update(final String name, final String description, final BigDecimal price){
        this.name = name;
        this.description = description;
        this.price = price;
        this.updatedAt = Instant.now();
        return this;
    }

    public void add(int quantity){
        if (quantity == 0){
            this.quantity++;
        }
        this.quantity += quantity;
        this.updatedAt = Instant.now();
    }

    public void remove(int quantity){
        if (quantity == 0){
            this.quantity--;
        }
        this.quantity -= quantity;
        this.updatedAt = Instant.now();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductType getType() {
        return type;
    }

    public Optional<ProductPhoto> getPhoto() {
        return Optional.ofNullable(photo);
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
