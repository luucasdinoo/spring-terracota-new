package com.terracota.infrastructure.config.usecase;

import com.terracota.application.sales.create.CreateSaleUseCase;
import com.terracota.application.sales.create.DefaultCreateSaleUseCase;
import com.terracota.domain.sales.SalesGateway;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import com.terracota.domain.user.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class SalesUseCaseConfig {

    private final SalesGateway salesGateway;
    private final CustomerGateway customerGateway;
    private final CraftsmanGateway craftsmanGateway;

    public SalesUseCaseConfig(
            final SalesGateway salesGateway,
            final CustomerGateway customerGateway,
            final CraftsmanGateway craftsmanGateway
    ) {
        this.salesGateway = Objects.requireNonNull(salesGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Bean
    public CreateSaleUseCase createSaleUseCase(){
        return new DefaultCreateSaleUseCase(salesGateway, customerGateway, craftsmanGateway);
    }
}
