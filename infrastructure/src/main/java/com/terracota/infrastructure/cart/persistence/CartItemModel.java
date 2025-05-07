package com.terracota.infrastructure.cart.persistence;

import com.terracota.domain.cart.CartItem;
import com.terracota.infrastructure.product.persistence.ProductModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "CartItem")
@Table(name = "cart_items")
@Getter @Setter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor(access = lombok.AccessLevel.PUBLIC)
public class CartItemModel {

    @Id
    private String id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartModel cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;

    public static CartItemModel from(final CartItem cartItem){
        return new CartItemModel(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getUnitPrice(),
                cartItem.getTotalPrice(),
                CartModel.from(cartItem.getCart()),
                ProductModel.from(cartItem.getProduct())
        );
    }

    public CartItem toDomain(){
        return CartItem.with(
                getId(),
                getQuantity(),
                getUnitPrice(),
                getTotalPrice(),
                getCart().toDomain(),
                getProduct().toDomain()
        );
    }
}
