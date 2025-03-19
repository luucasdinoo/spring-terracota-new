package com.terracota.infrastructure.user.customer.persistence;

import com.terracota.domain.user.ImagePhoto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "ImagePhoto")
@Table(name = "customers_image")
public class ImagePhotoModel {

    @Id
    private String id;

    @Column(name = "checksum", nullable = false)
    private String checksum;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    public ImagePhotoModel(){}

    private ImagePhotoModel(
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

    public static ImagePhotoModel from(final ImagePhoto image){
        return new ImagePhotoModel(
                image.id(),
                image.checksum(),
                image.name(),
                image.location()
        );
    }

    public ImagePhoto toDomain(){
        return ImagePhoto.with(
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
