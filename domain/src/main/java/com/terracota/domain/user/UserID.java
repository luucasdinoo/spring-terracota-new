package com.terracota.domain.user;

import com.terracota.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class UserID extends Identifier {

    private final String value;

    private UserID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static UserID unique(){
        return new UserID(UUID.randomUUID().toString().toLowerCase());
    }

    public static UserID from(String anId){
        return new UserID(anId);
    }
    public static UserID from(UUID anId){
        return new UserID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return Objects.equals(getValue(), userID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
