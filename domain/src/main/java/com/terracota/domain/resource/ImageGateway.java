package com.terracota.domain.resource;

import java.util.Optional;

public interface ImageGateway {

    ImagePhoto create(ImagePhoto imagePhoto);

    ImagePhoto save(ImagePhoto imagePhoto);

    Optional<ImagePhoto> findById(String anId);
}
