package com.terracota.domain.user;

import com.terracota.domain.ValueObject;

import java.util.Objects;

public class CPF extends ValueObject {

    private final String value;

    private CPF(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CPF from(final String value) {
        return new CPF(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(getValue(), cpf.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
