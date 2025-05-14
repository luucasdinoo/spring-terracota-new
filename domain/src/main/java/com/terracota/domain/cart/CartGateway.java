package com.terracota.domain.cart;

import java.util.Optional;

public interface CartGateway {

    void save(Cart cart);

    Optional<Cart> findById(CartID cartID);

    void addItem(CartItem item);

    void removeItem(CartItem item);
}
