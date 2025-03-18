package com.terracota.infrastructure.customer.persistence;

import com.terracota.customer.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEmbedded {

    @Column(name = "address_street")
    private String addressStreet;

    @Column(name = "address_number")
    private String addressNumber;

    @Column(name = "address_neighborhood")
    private String addressNeighborhood;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_state")
    private String addressState;

    @Column(name = "address_zip_code")
    private String addressZipCode;

    public AddressEmbedded(){}

    private AddressEmbedded(
            final String addressStreet,
            final String addressNumber,
            final String addressNeighborhood,
            final String addressCity,
            final String addressState,
            final String addressZipCode
    ){
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;
        this.addressNeighborhood = addressNeighborhood;
        this.addressCity = addressCity;
        this.addressState = addressState;
        this.addressZipCode = addressZipCode;
    }


    public static AddressEmbedded from(Address address) {
        return new AddressEmbedded(
                address.addressStreet(),
                address.addressNumber(),
                address.addressNeighborhood(),
                address.addressCity(),
                address.addressState(),
                address.addressZipCode()
        );
    }

    public Address toDomain(){
        return Address.with(
                getAddressStreet(),
                getAddressNumber(),
                getAddressNeighborhood(),
                getAddressCity(),
                getAddressState(),
                getAddressZipCode()
        );
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressNeighborhood() {
        return addressNeighborhood;
    }

    public void setAddressNeighborhood(String addressNeighborhood) {
        this.addressNeighborhood = addressNeighborhood;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }
}
