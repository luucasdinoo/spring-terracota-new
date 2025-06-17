package com.terracota.infrastructure.user;

import com.terracota.domain.resource.ImagePhoto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "ImagePhoto")
@Table(name = "resources")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ImagePhotoModel {

    @Id
    private String id;

    @Column(name = "checksum", nullable = false)
    private String checksum;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

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
}
