package com.terracota.application.sales.link;

import com.terracota.domain.sales.PaymentGateway;
import com.terracota.domain.sales.SaleItem;

import java.util.Objects;
import java.util.Set;

public class DefaultGenerateLinkUseCase extends GenerateLinkUseCase {

    private final PaymentGateway paymentGateway;

    public DefaultGenerateLinkUseCase(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public String execute(Set<SaleItem> input) {
        return this.paymentGateway.generateLink(input);
    }
}
