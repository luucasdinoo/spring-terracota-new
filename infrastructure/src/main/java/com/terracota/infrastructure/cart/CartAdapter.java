package com.terracota.infrastructure.cart;

import com.terracota.domain.cart.Cart;
import com.terracota.domain.cart.CartGateway;
import com.terracota.domain.cart.CartID;
import com.terracota.domain.cart.CartItem;
import com.terracota.infrastructure.cart.persistence.CartItemModel;
import com.terracota.infrastructure.cart.persistence.CartItemRepository;
import com.terracota.infrastructure.cart.persistence.CartModel;
import com.terracota.infrastructure.cart.persistence.CartRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CartAdapter implements CartGateway {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartAdapter(
            final CartRepository cartRepository,
            final CartItemRepository cartItemRepository
    ) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
        this.cartItemRepository =  Objects.requireNonNull(cartItemRepository);
    }

    @Override
    public void save(final Cart cart) {
        CartModel cartModel = CartModel.from(cart);
        cartRepository.save(cartModel).toDomain();
    }

    @Override
    public Optional<Cart> findById(final CartID cartID) {
        return cartRepository.findById(cartID.getValue())
                .map(CartModel::toDomain);
    }

    @Override
    public void addItem(final CartItem item) {
        cartItemRepository.save(CartItemModel.from(item));
    }

    @Override
    public void removeItem(final CartItem item) {
        cartItemRepository.delete(CartItemModel.from(item));
    }
}
