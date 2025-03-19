package com.terracota.domain.user.craftsman;

import com.terracota.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CraftsmanID extends Identifier {

    private final String value;

    private CraftsmanID(final String value) {
        this.value = value;
    }

    public static CraftsmanID unique(){
        return new CraftsmanID(UUID.randomUUID().toString().toLowerCase());
    }

    public static CraftsmanID from(String anId){
        return new CraftsmanID(anId);
    }
    public static CraftsmanID from(UUID anId){
        return new CraftsmanID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CraftsmanID that = (CraftsmanID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
