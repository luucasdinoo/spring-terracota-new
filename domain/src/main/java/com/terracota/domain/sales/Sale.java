package com.terracota.domain.sales;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.customer.Customer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;

public class Sale extends AggregateRoot<SaleID> {

    private Craftsman craftsman;

    private Customer customer;

    private Set<ProductID> productsIds;

    private Integer quantity;

    private BigDecimal total;

    private PaymentMethod paymentMethod;

    private Instant createdAt;

    private Sale(
            final SaleID saleID,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final Integer quantity,
            final PaymentMethod paymentMethod,
            final Instant createdAt
    ) {
        super(saleID);
        this.craftsman = craftsman;
        this.customer = customer;
        this.productsIds = productsIds;
        this.quantity = quantity;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
    }

    public static Sale newSale(
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final Integer quantity,
            final PaymentMethod paymentMethod

            ) {
        return new Sale(
                SaleID.unique(),
                craftsman,
                customer,
                productsIds,
                total,
                quantity,
                paymentMethod,
                Instant.now()
        );
    }

    public static Sale with(
            final SaleID saleID,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final Integer quantity,
            final PaymentMethod paymentMethod,
            final Instant createdAt
    ){
        return new Sale(saleID, craftsman, customer, productsIds, total, quantity, paymentMethod, createdAt);
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ProductID> getProductsIds() {
        return productsIds != null ? Collections.unmodifiableSet(productsIds) : Collections.emptySet();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
