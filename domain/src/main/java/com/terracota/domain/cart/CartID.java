package com.terracota.domain.cart;

import com.terracota.domain.Identifier;
import com.terracota.domain.utils.IdUtils;

import java.util.Objects;

public class CartID extends Identifier {

    private final String value;

    private CartID(final String value) {
        this.value = value;
    }

    public static CartID unique(){
        return new CartID(IdUtils.uuid());
    }

    public static CartID from(final String anId){
        return new CartID(anId);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartID cartID = (CartID) o;
        return Objects.equals(getValue(), cartID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
