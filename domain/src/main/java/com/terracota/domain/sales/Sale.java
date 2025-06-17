package com.terracota.domain.sales;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.product.Product;
import com.terracota.domain.user.customer.Customer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.Set;

public class Sale extends AggregateRoot<SaleID> {

    private Long paymentId;

    private Customer customer;

    private Set<Product> products;

    private BigDecimal total;

    private PaymentMethod paymentMethod;

    private String status;

    private Instant createdAt;

    private Sale(
            final SaleID saleID,
            final Long paymentId,
            final Customer customer,
            final Set<Product> products,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String status,
            final Instant createdAt
    ) {
        super(saleID);
        this.paymentId = paymentId;
        this.customer = customer;
        this.products = products;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Sale newSale(
            final SaleID saleID,
            final Long paymentId,
            final Customer customer,
            final Set<Product> products,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String status
            ) {
        return new Sale(
                saleID,
                paymentId,
                customer,
                products,
                total,
                paymentMethod,
                status,
                Instant.now()
        );
    }

    public static Sale with(
            final SaleID saleID,
            final Long paymentId,
            final Customer customer,
            final Set<Product> products,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String status,
            final Instant createdAt
    ){
        return new Sale(saleID, paymentId, customer, products, total, paymentMethod, status, createdAt);
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<Product> getProducts() {
        return products != null ? Collections.unmodifiableSet(products) : Collections.emptySet();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
