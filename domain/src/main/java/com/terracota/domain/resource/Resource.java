package com.terracota.domain.resource;

import com.terracota.domain.ValueObject;

public class Resource extends ValueObject {

    private final byte[] content;
    private final String checksum;
    private final String contentType;
    private final String name;

    private Resource(
            final byte[] content,
            final String checksum,
            final String contentType,
            final String name
    ) {
        this.checksum = checksum;
        this.content = content;
        this.contentType = contentType;
        this.name = name;
    }

    public static Resource with(
            final byte[] content,
            final String checksum,
            final String contentType,
            final String name
    ){
        return new Resource(content, checksum , contentType, name);
    }

    public byte[] content() {
        return content;
    }

    public String contentType() {
        return contentType;
    }

    public String name() {
        return name;
    }

    public String checksum(){
        return checksum;
    }


}
