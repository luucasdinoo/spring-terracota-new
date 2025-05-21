package com.terracota.infrastructure.resource;

import com.terracota.domain.Identifier;
import com.terracota.domain.resource.*;
import com.terracota.infrastructure.config.properties.StorageProperties;
import com.terracota.infrastructure.services.interfaces.StorageService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ResourceAdapter implements ResourceGateway {

    private final String filenamePattern;
    private final String locationPattern;
    private final StorageService storageService;

    public ResourceAdapter(
            final StorageProperties properties,
            final StorageService storageService
    ) {
        this.filenamePattern = properties.getFileNamePattern();
        this.locationPattern = properties.getLocationPattern();
        this.storageService = Objects.requireNonNull(storageService);
    }

    @Override
    public <T extends Identifier> ImagePhoto storeImage(T anId, MediaResource aResource) {
        Resource resource = aResource.resource();
        String filePath = filepath(anId, aResource.type());
        String url = store(filePath, resource);
        return ImagePhoto.with(resource.checksum(), resource.name(), url);
    }

    @Override
    public <T extends Identifier> void clearResources(T anId) {
    }

    private String type(final MediaType aType) {
        return filenamePattern.replace("type", aType.toString());
    }

    private <T extends Identifier> String user(final T anId) {
        return locationPattern.replace("userId", anId.getValue());
    }

    private <T extends Identifier> String filepath(final T anId, final MediaType aType) {
        return user(anId)
                .concat("-")
                .concat(type(aType));
    }

    private String store(final String filepath, final Resource aResource){
        return this.storageService.store(filepath, aResource);
    }
}
