package com.terracota.customer;

import com.terracota.ValueObject;

import java.util.Objects;

public class ImagePhoto extends ValueObject {

    private final String id;
    private final String checksum;
    private final String name;
    private final String location;

    private ImagePhoto(final String id, final String checksum, final String name, final String location) {
        this.id = id;
        this.checksum = checksum;
        this.name = name;
        this.location = location;
    }

    public static ImagePhoto with(final String id, final String checksum, final String name, final String location){
        return new ImagePhoto(id, checksum, name, location);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String location() {
        return location;
    }

    public String checksum() {
        return checksum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImagePhoto that = (ImagePhoto) o;
        return Objects.equals(checksum, that.checksum) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checksum, location);
    }
}
