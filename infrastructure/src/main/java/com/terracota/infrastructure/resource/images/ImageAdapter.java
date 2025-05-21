package com.terracota.infrastructure.resource.images;

import com.terracota.domain.resource.ImageGateway;
import com.terracota.domain.resource.ImagePhoto;
import com.terracota.infrastructure.user.ImagePhotoModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ImageAdapter implements ImageGateway {

    private final ImageRepository imageRepository;

    public ImageAdapter(final ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImagePhoto create(final ImagePhoto imagePhoto) {
        return persist(imagePhoto);
    }

    @Override
    public ImagePhoto save(ImagePhoto imagePhoto) {
        return persist(imagePhoto);
    }

    @Override
    public Optional<ImagePhoto> findById(final String anId) {
        return this.imageRepository.findById(anId)
                .map(ImagePhotoModel::toDomain);
    }

    private ImagePhoto persist(ImagePhoto imagePhoto) {
        return this.imageRepository.save(ImagePhotoModel.from(imagePhoto))
                .toDomain();
    }
}
