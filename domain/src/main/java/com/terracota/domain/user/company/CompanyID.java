package com.terracota.domain.user.company;

import com.terracota.domain.Identifier;
import com.terracota.domain.utils.IdUtils;

import java.util.Objects;

public class CompanyID extends Identifier {

    private final String value;

    private CompanyID(final String value) {
        this.value = value;
    }

    public static CompanyID unique(){
        return new CompanyID(IdUtils.uuid());
    }

    public static CompanyID from(String anId){
        return new CompanyID(anId);
    }


    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CompanyID companyID = (CompanyID) o;
        return Objects.equals(getValue(), companyID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
