package com.terracota.domain.sales;

import com.terracota.domain.product.ProductID;

import java.util.Set;

public interface PaymentGateway {

    void process(SaleID saleId, Set<ProductID> productIds);
}
