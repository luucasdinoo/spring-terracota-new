package com.terracota.domain.product;

import com.terracota.domain.ValueObject;

import java.util.Objects;

public class ProductPhoto extends ValueObject {

    private final String id;
    private final String checksum;
    private final String name;
    private final String location;

    private ProductPhoto(final String id, final String checksum, final String name, final String location) {
        this.id = id;
        this.checksum = checksum;
        this.name = name;
        this.location = location;
    }

    public static ProductPhoto with(final String id, final String checksum, final String name, final String location){
        return new ProductPhoto(id, checksum, name, location);
    }

    public String id() {
        return id;
    }

    public String checksum() {
        return checksum;
    }

    public String name() {
        return name;
    }

    public String location() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductPhoto that = (ProductPhoto) o;
        return Objects.equals(checksum, that.checksum) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checksum, location);
    }
}
