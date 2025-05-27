package com.terracota.application.files;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.resource.*;
import com.terracota.domain.user.craftsman.Craftsman;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.craftsman.CraftsmanID;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.util.Objects;
import java.util.Optional;

public class DefaultUploadImageUseCase extends UploadImageUseCase{

    private final ResourceGateway resourceGateway;
    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;
    private final ProductGateway productGateway;
    private final ImageGateway imageGateway;

    public DefaultUploadImageUseCase(
            final ResourceGateway resourceGateway,
            final CustomerGateway customerGateway,
            final CraftsmanGateway craftsmanGateway,
            final ProductGateway productGateway,
            final ImageGateway imageGateway
    ) {
        this.resourceGateway = Objects.requireNonNull(resourceGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
        this.imageGateway = Objects.requireNonNull(imageGateway);
    }

    @Override
    public void execute(final UploadImageCommand cmd) {
        String id = cmd.anId();
        Optional<Customer> customerOpt = this.customerGateway.findById(CustomerID.from(id));

        if (customerOpt.isPresent()){
            Customer customer = customerOpt.get();
            ImagePhoto photo = cmd.getResource()
                    .map(it -> this.resourceGateway.storeImage(customer.getId(), MediaResource.with(it, MediaType.USER_PHOTO)))
                    .orElse(null);

            customer.setPhoto(photo);
            this.customerGateway.update(customer);
            this.imageGateway.save(photo);
        } else {
            Optional<Craftsman> craftsmanOpt = this.craftsmanGateway.findById(CraftsmanID.from(id));

            if (craftsmanOpt.isPresent()) {
                Craftsman craftsman = craftsmanOpt.get();
                ImagePhoto photo = cmd.getResource()
                        .map(it -> this.resourceGateway.storeImage(craftsman.getId(), MediaResource.with(it, MediaType.USER_PHOTO)))
                        .orElse(null);

                craftsman.setPhoto(photo);
                this.craftsmanGateway.update(craftsman);
                this.imageGateway.save(photo);
            } else {
                Optional<Product> productOpt = this.productGateway.findById(ProductID.from(id));
                if (productOpt.isPresent()){
                    Product product = productOpt.get();
                    ImagePhoto photo = cmd.getResource()
                            .map(it -> this.resourceGateway.storeImage(product.getId(), MediaResource.with(it, MediaType.PRODUCT_PHOTO)))
                            .orElse(null);

                    product.setPhoto(photo);
                    this.productGateway.update(product);
                    this.imageGateway.save(photo);
                }
                else {
                    throw EntityNotFoundException.with("Entity not found");
                }
            }
        }
    }
}
