package com.terracota.infrastructure.user.customer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terracota.domain.user.Address;

public record AddressRequest(
        @JsonProperty("address_street") String addressStreet,
        @JsonProperty("address_number") String addressNumber,
        @JsonProperty("address_neighborhood") String addressNeighborhood,
        @JsonProperty("address_city") String addressCity,
        @JsonProperty("address_state") String addressState,
        @JsonProperty("address_zip_code") String addressZipCode
) {

    public Address toDomain(){
        return Address.with(
                addressStreet(),
                addressNumber(),
                addressNeighborhood(),
                addressCity(),
                addressState(),
                addressZipCode()
        );
    }
}
