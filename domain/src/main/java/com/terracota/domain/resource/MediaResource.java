package com.terracota.domain.resource;

import java.util.Objects;

public class MediaResource {

    private Resource resource;
    private MediaType type;

    public MediaResource(final Resource aResource, final MediaType type) {
        this.resource = aResource;
        this.type = type;
    }

    public static MediaResource with(final Resource resource, final MediaType aType){
        return new MediaResource(resource, aType);
    }

    public Resource resource() {
        return resource;
    }

    public MediaType type() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MediaResource that = (MediaResource) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }
}
