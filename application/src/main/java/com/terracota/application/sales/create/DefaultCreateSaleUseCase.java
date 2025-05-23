package com.terracota.application.sales.create;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.sales.*;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultCreateSaleUseCase extends CreateSaleUseCase{

    private final SalesGateway salesGateway;
    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;
    private final PaymentGateway paymentGateway;

    public DefaultCreateSaleUseCase(
            final SalesGateway salesGateway,
            final CustomerGateway customerGateway,
            final CraftsmanGateway craftsmanGateway, PaymentGateway paymentGateway
    ) {
        this.salesGateway = Objects.requireNonNull(salesGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public CreateSaleOutput execute(final CreateSaleCommand input) {
        CustomerID customerId = CustomerID.from(input.customerId());
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());

        Customer customer = this.customerGateway.findById(customerId)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, customerId));
        Craftsman craftsman = this.craftsmanGateway.findById(craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Craftsman.class, craftsmanId));
        Set<ProductID> productIds = input.productsIds()
                .stream().map(ProductID::from).collect(Collectors.toSet());
        PaymentMethod paymentMethod = PaymentMethod.of(input.paymentMethod())
                .orElseThrow(() -> DomainException.with("Invalid payment method"));

        SaleID saleId = SaleID.unique();
        this.paymentGateway.process(saleId, productIds);

        Sale sale = Sale.newSale(
                saleId,
                craftsman,
                customer,
                productIds,
                BigDecimal.valueOf(input.total()),
                paymentMethod,
                input.nsu(),
                input.aut()
        );

        return CreateSaleOutput.from(this.salesGateway.create(sale));
    }
}
