package com.terracota.infrastructure.product.persistence;

import com.terracota.domain.product.ProductPhoto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "ProductPhoto")
@Table(name = "products_image")
public class ProductPhotoModel {

    @Id
    private String id;

    @Column(name = "checksum", nullable = false)
    private String checksum;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    public ProductPhotoModel(){}

    private ProductPhotoModel(
            final String id,
            final String checksum,
            final String name,
            final String location
    ) {
        this.id = id;
        this.checksum = checksum;
        this.name = name;
        this.location = location;
    }

    public static ProductPhotoModel from(final ProductPhoto photo){
        return new ProductPhotoModel(
                photo.id(),
                photo.checksum(),
                photo.name(),
                photo.location()
        );
    }

    public ProductPhoto toDomain(){
        return ProductPhoto.with(
                getId(),
                getChecksum(),
                getName(),
                getLocation()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
