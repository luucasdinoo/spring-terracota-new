package com.terracota.infrastructure.sales.persistence;

import com.terracota.domain.sales.PaymentMethod;
import com.terracota.domain.sales.Sale;
import com.terracota.domain.sales.SaleID;
import com.terracota.infrastructure.product.persistence.ProductModel;
import com.terracota.infrastructure.user.customer.persistence.CustomerModel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Sale")
@Table(name = "sales")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SaleModel {

    @Id
    private String preferenceId;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToMany
    @JoinTable(
            name = "sale_products",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductModel> products;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public static SaleModel from(final Sale sale){
        return new SaleModel(
                sale.getId().getValue(),
                sale.getPaymentId(),
                CustomerModel.from(sale.getCustomer()),
                sale.getPaymentMethod(),
                sale.getStatus(),
                sale.getProducts().stream()
                        .map(ProductModel::from)
                        .collect(Collectors.toSet()),
                sale.getTotal(),
                sale.getCreatedAt()
        );
    }

    public Sale toDomain(){
        return Sale.with(
                SaleID.from(getPreferenceId()),
                getPaymentId(),
                getCustomer().toDomain(),
                getProducts().stream()
                        .map(ProductModel::toDomain)
                        .collect(Collectors.toSet()),
                getTotal(),
                getPaymentMethod(),
                getStatus(),
                getCreatedAt()
        );
    }
}
