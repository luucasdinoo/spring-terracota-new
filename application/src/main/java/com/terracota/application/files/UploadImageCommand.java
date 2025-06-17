package com.terracota.application.files;

import com.terracota.domain.resource.Resource;

import java.util.Optional;

public record UploadImageCommand(
        Resource resource,
        String anId
) {
    public static UploadImageCommand with(
            final Resource resource,
            String anId
    ) {
        return new UploadImageCommand(resource, anId);
    }

    public Optional<Resource> getResource(){
        return Optional.ofNullable(resource);
    }
}
