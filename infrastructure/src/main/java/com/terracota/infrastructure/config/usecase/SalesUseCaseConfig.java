package com.terracota.infrastructure.config.usecase;

import com.terracota.application.sales.create.CreateSaleUseCase;
import com.terracota.application.sales.create.DefaultCreateSaleUseCase;
import com.terracota.application.sales.link.DefaultGenerateLinkUseCase;
import com.terracota.application.sales.link.GenerateLinkUseCase;
import com.terracota.domain.sales.PaymentGateway;
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
     private final PaymentGateway paymentGateway;

    public SalesUseCaseConfig(
            final SalesGateway salesGateway,
            final CustomerGateway customerGateway,
            final CraftsmanGateway craftsmanGateway,
            final PaymentGateway paymentGateway
    ) {
        this.salesGateway = Objects.requireNonNull(salesGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Bean
    public CreateSaleUseCase createSaleUseCase(){
        return new DefaultCreateSaleUseCase(salesGateway, customerGateway, craftsmanGateway, paymentGateway);
    }

    @Bean
    public GenerateLinkUseCase generateLinkUseCase(){
        return new DefaultGenerateLinkUseCase(paymentGateway);
    }
}
