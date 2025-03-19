package com.terracota.domain.user.customer;

import com.terracota.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class CustomerID extends Identifier {

    private final String value;

    private CustomerID(final String value) {
        this.value = value;
    }

    public static CustomerID unique(){
        return new CustomerID(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
    }

    public static CustomerID from(String anId){
        return new CustomerID(anId);
    }

    public static CustomerID from(UUID anId){
        return new CustomerID(anId.toString().toLowerCase().replace("-", ""));
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CustomerID that = (CustomerID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
