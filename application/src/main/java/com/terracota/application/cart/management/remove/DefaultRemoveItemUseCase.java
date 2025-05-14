package com.terracota.application.cart.management.remove;

import com.terracota.domain.cart.Cart;
import com.terracota.domain.cart.CartGateway;
import com.terracota.domain.cart.CartItem;
import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.util.Objects;
import java.util.Optional;

public class DefaultRemoveItemUseCase extends RemoveItemUseCase{

    private final CartGateway cartGateway;
    private final ProductGateway productGateway;
    private final CustomerGateway customerGateway;

    public DefaultRemoveItemUseCase(
            final CartGateway cartGateway,
            final ProductGateway productGateway,
            final CustomerGateway customerGateway
    ) {
        this.cartGateway = Objects.requireNonNull(cartGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
    }

    @Override
    public void execute(RemoveItemCommand input) {
        ProductID productId = ProductID.from(input.productId());
        CraftsmanID craftsmanId = CraftsmanID.from(input.craftsmanId());
        CustomerID customerId = CustomerID.from(input.customerId());

        Product product = productGateway.findById(productId, craftsmanId)
                .orElseThrow(() -> EntityNotFoundException.with(Product.class, productId));

        Customer customer = customerGateway.findById(customerId)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, customerId));

        Cart cart = cartGateway.findById(customer.getCartId())
                .orElseThrow(() -> EntityNotFoundException.with(Cart.class, customer.getCartId()));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().getValue().equals(productId.getValue()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.remove(input.quantity());
            if (item.getQuantity() <= 0) {
                cart.removeItem(item);
            } else {
                item.calculateTotalPrice();
            }
            cart.calculateTotal();
            cartGateway.save(cart);
        }
    }
}
