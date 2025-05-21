package com.terracota.domain.sales;

import com.terracota.domain.Identifier;
import com.terracota.domain.utils.IdUtils;

import java.util.Objects;

public class SaleID extends Identifier {

    private final String value;

    public SaleID(final String value) {
        this.value = value;
    }

    public static SaleID unique(){
        return new SaleID(IdUtils.uuid());
    }

    public static SaleID from(final String anId){
        return new SaleID(anId);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SaleID saleID = (SaleID) o;
        return Objects.equals(getValue(), saleID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
