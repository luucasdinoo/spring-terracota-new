package com.terracota.domain.product;

import com.terracota.domain.Identifier;
import com.terracota.domain.utils.IdUtils;

import java.util.Objects;

public class ProductID extends Identifier {

    private final String value;

    private ProductID(final String value) {
        this.value = value;
    }

    public static ProductID unique(){
        return new ProductID(IdUtils.uuid());
    }

    public static ProductID from(final String anId){
        return new ProductID(anId);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return Objects.equals(getValue(), productID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
