package com.terracota.application.sales.create;

import java.util.Set;

public record CreateSaleCommand(
        String preferenceId,
        Long paymentId,
        String craftsmanId,
        String customerId,
        Set<String> productsIds,
        Long total,
        String paymentMethod,
        String status
) {
    public static CreateSaleCommand with(
            final String preferenceId,
            final Long paymentId,
            final String craftsmanId,
            final String customerId,
            final Set<String> productsIds,
            final Long total,
            final String paymentMethod,
            final String status
    ){
        return new CreateSaleCommand(preferenceId, paymentId, craftsmanId, customerId, productsIds, total, paymentMethod, status);
    }
}
