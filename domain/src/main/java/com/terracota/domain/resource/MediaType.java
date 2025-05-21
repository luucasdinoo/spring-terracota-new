package com.terracota.domain.resource;

import java.util.Arrays;
import java.util.Optional;

public enum MediaType {
    PHOTO,
    BANNER;

    public static Optional<MediaType> of(final String value){
        return Arrays.stream(values())
                .filter(mt -> mt.name().equalsIgnoreCase(value))
                .findFirst();
    }
}
