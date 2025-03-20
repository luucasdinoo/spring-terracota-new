package com.terracota.domain.user;

import com.terracota.domain.ValueObject;

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

    public static Address with(final Address address) {
        return new Address(
                address.getAddressStreet(),
                address.getAddressNumber(),
                address.getAddressNeighborhood(),
                address.getAddressCity(),
                address.getAddressState(),
                address.getAddressZipCode()
        );
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public String getAddressNeighborhood() {
        return addressNeighborhood;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }
}
