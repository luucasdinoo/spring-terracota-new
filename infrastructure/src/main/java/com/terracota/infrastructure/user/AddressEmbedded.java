package com.terracota.infrastructure.user;

import com.terracota.domain.user.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
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


    public static AddressEmbedded from(final Address address) {
        return new AddressEmbedded(
                address.getAddressStreet(),
                address.getAddressNumber(),
                address.getAddressNeighborhood(),
                address.getAddressCity(),
                address.getAddressState(),
                address.getAddressZipCode()
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
}
