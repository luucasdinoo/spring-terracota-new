package com.terracota.infrastructure.config.usecase;

import com.terracota.application.customer.create.CreateCustomerUseCase;
import com.terracota.application.customer.create.DefaultCreateCustomerUseCase;
import com.terracota.application.customer.delete.DefaultDeleteCustomerUseCase;
import com.terracota.application.customer.delete.DeleteCustomerUseCase;
import com.terracota.application.customer.retrieve.get.DefaultGetCustomerByEmailUseCase;
import com.terracota.application.customer.retrieve.get.DefaultGetCustomerBydIdUseCase;
import com.terracota.application.customer.retrieve.get.GetCustomerByEmailUseCase;
import com.terracota.application.customer.retrieve.get.GetCustomerBydIdUseCase;
import com.terracota.application.customer.retrieve.list.DefaultListCustomerUseCase;
import com.terracota.application.customer.retrieve.list.ListCustomerUseCase;
import com.terracota.application.customer.update.DefaultUpdateCustomerUseCase;
import com.terracota.application.customer.update.UpdateCustomerUseCase;
import com.terracota.application.files.DefaultUploadImageUseCase;
import com.terracota.application.files.UploadImageUseCase;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.resource.ImageGateway;
import com.terracota.domain.resource.ResourceGateway;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;
    private final ResourceGateway resourceGateway;
    private final ProductGateway productGateway;
    private final ImageGateway imageGateway;

    public CustomerUseCaseConfig(
            final CustomerGateway customerGateway,
            final CraftsmanGateway craftsmanGateway,
            final ResourceGateway resourceGateway,
            final ProductGateway productGateway,
            final ImageGateway imageGateway
    ) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
        this.resourceGateway = Objects.requireNonNull(resourceGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
        this.imageGateway = Objects.requireNonNull(imageGateway);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(){
        return new DefaultCreateCustomerUseCase(customerGateway);
    }

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(){
        return new DefaultUpdateCustomerUseCase(customerGateway);
    }

    @Bean
    public DeleteCustomerUseCase deleteCustomerUseCase(){
        return new DefaultDeleteCustomerUseCase(customerGateway);
    }

    @Bean
    public GetCustomerBydIdUseCase getCustomerBydIdUseCase(){
        return new DefaultGetCustomerBydIdUseCase(customerGateway);
    }

    @Bean
    public GetCustomerByEmailUseCase getCustomerByEmailUseCase(){
        return new DefaultGetCustomerByEmailUseCase(customerGateway);
    }

    @Bean
    public ListCustomerUseCase listCustomerUseCase(){
        return new DefaultListCustomerUseCase(customerGateway);
    }

    @Bean
    public UploadImageUseCase uploadImageUseCase(){
        return new DefaultUploadImageUseCase(resourceGateway, customerGateway, craftsmanGateway, productGateway, imageGateway);
    }

}
