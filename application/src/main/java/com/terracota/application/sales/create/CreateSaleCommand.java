package com.terracota.application.sales.create;

import java.util.Set;

public record CreateSaleCommand(
        String craftsmanId,
        String customerId,
        Set<String> productsIds,
        long total,
        String paymentMethod,
        String nsu,
        Long aut
) {
    public static CreateSaleCommand with(
            final String craftsmanId,
            final String customerId,
            final Set<String> productsIds,
            final long total,
            final String paymentMethod,
            final String nsu,
            final Long aut
    ){
        return new CreateSaleCommand(craftsmanId, customerId, productsIds, total, paymentMethod, nsu, aut);
    }
}
