package com.terracota.customer;

import com.terracota.Identifier;

import java.util.Objects;

public class CPF extends Identifier {

    private final String value;

    private CPF(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CPF from(final String value) {
        return new CPF(value);
    }

    @Override
    public String getValue() {
        return this.value;
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
