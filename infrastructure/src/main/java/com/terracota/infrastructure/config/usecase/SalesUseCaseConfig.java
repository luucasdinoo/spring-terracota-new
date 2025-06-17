package com.terracota.infrastructure.config.usecase;

import com.terracota.application.sales.create.CreateSaleUseCase;
import com.terracota.application.sales.create.DefaultCreateSaleUseCase;
import com.terracota.application.sales.link.DefaultGenerateLinkUseCase;
import com.terracota.application.sales.link.GenerateLinkUseCase;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.sales.PaymentGateway;
import com.terracota.domain.sales.SalesGateway;
import com.terracota.domain.user.customer.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class SalesUseCaseConfig {

    private final SalesGateway salesGateway;
    private final ProductGateway productGateway;
    private final CustomerGateway customerGateway;
    private final PaymentGateway paymentGateway;

    public SalesUseCaseConfig(
            final SalesGateway salesGateway,
            final ProductGateway productGateway,
            final CustomerGateway customerGateway,
            final PaymentGateway paymentGateway
    ) {
        this.salesGateway = Objects.requireNonNull(salesGateway);
        this.productGateway = Objects.requireNonNull(productGateway);
        this.customerGateway = Objects.requireNonNull(customerGateway);
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Bean
    public CreateSaleUseCase createSaleUseCase(){
        return new DefaultCreateSaleUseCase(salesGateway, productGateway, customerGateway);
    }

    @Bean
    public GenerateLinkUseCase generateLinkUseCase(){
        return new DefaultGenerateLinkUseCase(paymentGateway);
    }
}
