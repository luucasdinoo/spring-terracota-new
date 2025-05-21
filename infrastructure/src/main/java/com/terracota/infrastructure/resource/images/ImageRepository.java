package com.terracota.infrastructure.resource.images;

import com.terracota.infrastructure.user.ImagePhotoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImagePhotoModel, String> {
}
