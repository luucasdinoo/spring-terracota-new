package com.terracota.domain.cart;

import com.terracota.domain.product.Product;
import com.terracota.domain.utils.IdUtils;

import java.math.BigDecimal;

public class CartItem {

    private String id;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private Cart cart;

    private Product product;

    private CartItem(
            final String id,
            final Integer quantity,
            final BigDecimal unitPrice,
            final BigDecimal totalPrice,
            final Cart cart,
            final Product product
    ) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.cart = cart;
        this.product = product;
    }

    public static CartItem newCartItem(
            final Integer quantity,
            final BigDecimal unitPrice,
            final Cart cart,
            final Product product
    ) {
        return new CartItem(IdUtils.uuid(), quantity, unitPrice, null, cart, product);
    }

    public static CartItem with(
            final String id,
            final Integer quantity,
            final BigDecimal unitPrice,
            final BigDecimal totalPrice,
            final Cart cart,
            final Product product
    ) {
        return new CartItem(id, quantity, unitPrice, totalPrice, cart, product);
    }

    public void calculateTotalPrice(){
        BigDecimal unitPrice = getUnitPrice() != null ? getUnitPrice() : BigDecimal.ZERO;
        int quantity = getQuantity() != null ? getQuantity() : 0;
        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }

    public void add(final int qtd){
        this.quantity += qtd;
    }

    public void remove(final int qtd){
        this.quantity -= qtd;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

}
