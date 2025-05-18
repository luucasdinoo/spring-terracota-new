package com.terracota.infrastructure.sales.persistence;

import com.terracota.domain.product.ProductID;
import com.terracota.domain.sales.PaymentMethod;
import com.terracota.domain.sales.Sale;
import com.terracota.domain.sales.SaleID;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
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
    private String id;

    @ManyToOne
    @JoinColumn(name = "craftsman_id", nullable = false)
    private CraftsmanModel craftsman;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "nsu", nullable = false)
    private String nsu;

    @Column(name = "aut", nullable = false)
    private Long aut;

    @ElementCollection
    @CollectionTable(name = "sale_products", joinColumns = @JoinColumn(name = "sale_id"))
    private Set<String> productsIds;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public static SaleModel from(final Sale sale){
        return new SaleModel(
                sale.getId().getValue(),
                CraftsmanModel.from(sale.getCraftsman()),
                CustomerModel.from(sale.getCustomer()),
                sale.getPaymentMethod(),
                sale.getNsu(),
                sale.getAut(),
                sale.getProductsIds().stream()
                        .map(ProductID::getValue).collect(Collectors.toSet()),
                sale.getTotal(),
                sale.getCreatedAt()
        );
    }

    public Sale toDomain(){
        return Sale.with(
                SaleID.from(getId()),
                getCraftsman().toDomain(),
                getCustomer().toDomain(),
                getProductsIds().stream()
                        .map(ProductID::from)
                        .collect(Collectors.toSet()),
                getTotal(),
                getPaymentMethod(),
                getNsu(),
                getAut(),
                getCreatedAt()
        );
    }
}
