package com.terracota.domain.sales;

import com.terracota.domain.Identifier;

import java.util.Objects;

public class SaleID extends Identifier {

    private final String preferenceId;

    public SaleID(final String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public static SaleID from(final String preferenceId) {
        return new SaleID(preferenceId);
    }

    @Override
    public String getValue() {
        return this.preferenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaleID saleID = (SaleID) o;
        return Objects.equals(preferenceId, saleID.preferenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(preferenceId);
    }
}
