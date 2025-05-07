package com.terracota.infrastructure.cart.persistence;

import com.terracota.domain.cart.Cart;
import com.terracota.domain.cart.CartID;
import com.terracota.infrastructure.user.customer.persistence.CustomerModel;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Cart")
@Table(name = "carts")
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CartModel {

    @Id
    private String id;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "shipping_fee")
    private BigDecimal shippingFee;

    @Column(name = "total")
    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CartItemModel> items;

    public static CartModel from(final Cart cart){
        return new CartModel(
                cart.getId().getValue(),
                cart.getSubTotal(),
                cart.getShippingFee(),
                cart.getTotal(),
                CustomerModel.from(cart.getCustomer()),
                cart.getItems().stream()
                        .map(CartItemModel::from)
                        .collect(Collectors.toSet())
        );
    }

    public Cart toDomain(){
        return Cart.with(
                CartID.from(getId()),
                getSubTotal(),
                getShippingFee(),
                getTotal(),
                getCustomer().toDomain(),
                getItems().stream()
                        .map(CartItemModel::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
