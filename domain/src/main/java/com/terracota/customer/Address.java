package com.terracota.customer;

import com.terracota.ValueObject;

public class Address extends ValueObject {

    private final String addressStreet;
    private final String addressNumber;
    private final String addressNeighborhood;
    private final String addressCity;
    private final String addressState;
    private final String addressZipCode;

    private Address(
            final String addressStreet,
            final String addressNumber,
            final String addressNeighborhood,
            final String addressCity,
            final String addressState,
            final String addressZipCode
    ) {
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
    }

    public static Address with(
            final String addressStreet,
            final String addressNumber,
            final String addressNeighborhood,
            final String addressCity,
            final String addressState,
            final String addressZipCode
    ){
        return new Address(addressStreet, addressNumber, addressNeighborhood, addressCity, addressState, addressZipCode);
    }

    public String addressStreet() {
        return addressStreet;
    }

    public String addressNumber() {
        return addressNumber;
    }

    public String addressNeighborhood() {
        return addressNeighborhood;
    }

    public String addressCity() {
        return addressCity;
    }

    public String addressState() {
        return addressState;
    }

    public String addressZipCode() {
        return addressZipCode;
    }
}
