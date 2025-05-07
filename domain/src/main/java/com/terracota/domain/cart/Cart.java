package com.terracota.domain.cart;

import com.terracota.domain.AggregateRoot;
import com.terracota.domain.user.customer.Customer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Cart extends AggregateRoot<CartID> {

    private BigDecimal subTotal;

    private BigDecimal shippingFee;

    private BigDecimal total;

    private Customer customer;

    private Set<CartItem> items;

    private Cart(
            final CartID cartID,
            final BigDecimal subTotal,
            final BigDecimal shippingFee,
            final BigDecimal total,
            final Customer customer,
            final Set<CartItem> items
    ) {
        super(cartID);
        this.subTotal = subTotal;
        this.shippingFee = shippingFee;
        this.total = total;
        this.customer = customer;
        this.items = items;
    }

    public static Cart newCart(Customer customer) {
        CartID cartID = CartID.unique();
        return new Cart(cartID, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, customer, new HashSet<>());
    }

    public static Cart with(
            final CartID cartID,
            final BigDecimal subTotal,
            final BigDecimal shippingFee,
            final BigDecimal total,
            final Customer customer,
            final Set<CartItem> items
    ){
        return new Cart(cartID, subTotal, shippingFee, total, customer, items);
    }

    public void calculateTotal(){
        getItems().forEach(CartItem::calculateTotalPrice);

        this.subTotal = getItems().stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = this.subTotal.add(shippingFee);
    }

    public void addItem(final CartItem item) {
        this.items.add(item);
    }

    public void removeItem(final CartItem item) {
        this.items.remove(item);
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<CartItem> getItems() {
        return Collections.unmodifiableSet(items);
    }
}
