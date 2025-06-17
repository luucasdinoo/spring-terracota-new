package com.terracota.domain.resource;

import java.util.Arrays;
import java.util.Optional;

public enum MediaType {
    USER_PHOTO,
    USER_BANNER,
    PRODUCT_PHOTO;

    public static Optional<MediaType> of(final String value){
        return Arrays.stream(values())
                .filter(mt -> mt.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
