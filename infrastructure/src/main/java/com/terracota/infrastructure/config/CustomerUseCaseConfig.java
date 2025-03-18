package com.terracota.infrastructure.config;

import com.terracota.customer.CustomerGateway;
import com.terracota.customer.create.CreateCustomerUseCase;
import com.terracota.customer.create.DefaultCreateCustomerUseCase;
import com.terracota.customer.delete.DefaultDeleteCustomerUseCase;
import com.terracota.customer.delete.DeleteCustomerUseCase;
import com.terracota.customer.retrieve.get.DefaultGetCustomerBydIdUseCase;
import com.terracota.customer.retrieve.get.GetCustomerBydIdUseCase;
import com.terracota.customer.retrieve.list.DefaultListCustomerUseCase;
import com.terracota.customer.retrieve.list.ListCustomerUseCase;
import com.terracota.customer.update.DefaultUpdateCustomerUseCase;
import com.terracota.customer.update.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;

    public CustomerUseCaseConfig(final CustomerGateway customerGateway) {
        this.customerGateway = Objects.requireNonNull(customerGateway);
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
    public ListCustomerUseCase listCustomerUseCase(){
        return new DefaultListCustomerUseCase(customerGateway);
    }

}
