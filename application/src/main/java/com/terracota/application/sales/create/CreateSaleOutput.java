package com.terracota.application.sales.create;


import com.terracota.domain.sales.Sale;

public record CreateSaleOutput(String orderId) {
    public static CreateSaleOutput from(final Sale sale) {
        return new CreateSaleOutput(sale.getId().getValue());
    }
}
