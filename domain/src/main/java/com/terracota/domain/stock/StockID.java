package com.terracota.domain.stock;

import com.terracota.domain.Identifier;
import com.terracota.domain.utils.IdUtils;

import java.util.Objects;

public class StockID extends Identifier {

    private final String value;

    private StockID(final String value) {
        this.value = value;
    }

    public static StockID unique(){
        return new StockID(IdUtils.uuid());
    }

    public static StockID from(final String id){
        return new StockID(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockID stockID = (StockID) o;
        return Objects.equals(getValue(), stockID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String getValue() {
        return value;
    }
}
