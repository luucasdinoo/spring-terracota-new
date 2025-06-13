package com.terracota.application.sales.create;

import com.terracota.domain.exceptions.DomainException;
import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.sales.PaymentMethod;
import com.terracota.domain.sales.Sale;
import com.terracota.domain.sales.SaleID;
import com.terracota.domain.sales.SalesGateway;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DefaultCreateSaleUseCase extends CreateSaleUseCase{

    private final SalesGateway salesGateway;
    private final ProductGateway productGateway;
    private final CustomerGateway customerGateway;

    public DefaultCreateSaleUseCase(
            final SalesGateway salesGateway,
            final ProductGateway productGateway1,
            final CustomerGateway customerGateway
    ) {
        this.salesGateway = Objects.requireNonNull(salesGateway);
        this.productGateway = productGateway1;
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public CreateSaleOutput execute(final CreateSaleCommand input) {
        CustomerID customerId = CustomerID.from(input.customerId());

        Customer customer = this.customerGateway.findById(customerId)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, customerId));

        PaymentMethod paymentMethod = PaymentMethod.of(input.paymentMethod())
                .orElseThrow(() -> DomainException.with("Invalid payment method"));

        Set<Product> products = new HashSet<>();

        input.productsIds().forEach(productId -> {
            ProductID productID = ProductID.from(productId);
            Product product = this.productGateway.findById(productID)
                    .orElseThrow(() -> EntityNotFoundException.with(Product.class, productID));
            products.add(product);
        });

        Sale sale = Sale.newSale(
                SaleID.from(input.preferenceId()),
                input.paymentId(),
                customer,
                products,
                new BigDecimal(input.total()),
                paymentMethod,
                input.status()
        );

        return CreateSaleOutput.from(this.salesGateway.create(sale));
    }
}
