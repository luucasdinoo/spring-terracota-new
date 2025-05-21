package com.terracota.application.files;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.domain.resource.*;
import com.terracota.domain.user.customer.Customer;
import com.terracota.domain.user.customer.CustomerGateway;
import com.terracota.domain.user.customer.CustomerID;

import java.util.Objects;

public class DefaultUploadImageUseCase extends UploadImageUseCase{

    private final ResourceGateway resourceGateway;
    private final CustomerGateway customerGateway;
    private final ImageGateway imageGateway;

    public DefaultUploadImageUseCase(
            final ResourceGateway resourceGateway,
            final CustomerGateway customerGateway,
            final ImageGateway imageGateway
    ) {
        this.resourceGateway = Objects.requireNonNull(resourceGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.imageGateway = Objects.requireNonNull(imageGateway);
    }

    @Override
    public void execute(final UploadImageCommand cmd) {
        CustomerID customerId = CustomerID.from(cmd.anId());
        Customer customer = this.customerGateway.findById(customerId)
                .orElseThrow(() -> EntityNotFoundException.with(Customer.class, customerId));

            ImagePhoto photo = cmd.getResource()
                    .map(it -> this.resourceGateway.storeImage(customerId, MediaResource.with(it, MediaType.PHOTO)))
                    .orElse(null);

            customer.setPhoto(photo);
            this.customerGateway.update(customer);
            this.imageGateway.save(photo);
    }
}
