package com.terracota.domain.sales;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.customer.Customer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

public class Sale extends AggregateRoot<SaleID> {

    private Long paymentId;

    private Craftsman craftsman;

    private Customer customer;

    private Set<ProductID> productsIds;

    private BigDecimal total;

    private PaymentMethod paymentMethod;

    private String status;

    private Instant createdAt;

    private Sale(
            final SaleID saleID,
            final Long paymentId,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String status,
            final Instant createdAt
    ) {
        super(saleID);
        this.paymentId = paymentId;
        this.craftsman = craftsman;
        this.customer = customer;
        this.productsIds = productsIds;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Sale newSale(
            final SaleID saleID,
            final Long paymentId,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String status
            ) {
        return new Sale(
                saleID,
                paymentId,
                craftsman,
                customer,
                productsIds,
                total,
                paymentMethod,
                status,
                Instant.now()
        );
    }

    public static Sale with(
            final SaleID saleID,
            final Long paymentId,
            final Craftsman craftsman,
            final Customer customer,
            final Set<ProductID> productsIds,
            final BigDecimal total,
            final PaymentMethod paymentMethod,
            final String status,
            final Instant createdAt
    ){
        return new Sale(saleID, paymentId, craftsman, customer, productsIds, total, paymentMethod, status, createdAt);
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Craftsman getCraftsman() {
        return craftsman;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ProductID> getProductsIds() {
        return productsIds;
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
