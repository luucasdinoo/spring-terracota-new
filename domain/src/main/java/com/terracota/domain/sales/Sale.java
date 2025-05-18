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

    private BigDecimal total;

    private PaymentMethod paymentMethod;

    private String nsu;

    private Long aut;

    private Instant createdAt;

    private Sale(
            final SaleID saleID,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String nsu,
            final Long aut,
            final Instant createdAt
    ) {
        super(saleID);
        this.craftsman = craftsman;
        this.customer = customer;
        this.productsIds = productsIds;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.nsu = nsu;
        this.aut = aut;
        this.createdAt = createdAt;
    }

    public static Sale newSale(
            final SaleID saleID,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String nsu,
            final Long aut

            ) {
        return new Sale(
                saleID,
                craftsman,
                customer,
                productsIds,
                total,
                paymentMethod,
                nsu,
                aut,
                Instant.now()
        );
    }

    public static Sale with(
            final SaleID saleID,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String nsu,
            final Long aut,
            final Instant createdAt
    ){
        return new Sale(saleID, craftsman, customer, productsIds, total, paymentMethod, nsu, aut, createdAt);
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

    public BigDecimal getTotal() {
        return total;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getNsu() {
        return nsu;
    }

    public Long getAut() {
        return aut;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
