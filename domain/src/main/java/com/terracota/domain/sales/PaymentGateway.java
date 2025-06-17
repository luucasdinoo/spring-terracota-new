package com.terracota.domain.sales;


import java.util.Set;

public interface PaymentGateway {

    String generateLink(Set<SaleItem> saleItems);

}
