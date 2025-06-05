package com.terracota.domain.user;

import com.terracota.domain.ValueObject;

import java.util.Objects;

public class CNPJ extends ValueObject {

    private final String value;

    private CNPJ(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CNPJ from(final String value) {
        return new CNPJ(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CNPJ cnpj = (CNPJ) o;
        return Objects.equals(getValue(), cnpj.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
