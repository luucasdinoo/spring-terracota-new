package com.terracota.infrastructure.config.usecase;

import com.terracota.application.company.create.CreateCompanyUseCase;
import com.terracota.application.company.create.DefaultCreateCompanyUseCase;
import com.terracota.application.company.retrieve.get.DefaultGetCompanyByIdUseCase;
import com.terracota.application.company.retrieve.get.GetCompanyByIdUseCase;
import com.terracota.application.company.retrieve.list.DefaultListCompaniesUseCase;
import com.terracota.application.company.retrieve.list.ListCompaniesUseCase;
import com.terracota.application.company.update.DefaultUpdateCompanyUseCase;
import com.terracota.application.company.update.UpdateCompanyUseCase;
import com.terracota.domain.user.company.CompanyGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CompanyUseCaseConfig {

    private final CompanyGateway companyGateway;

    public CompanyUseCaseConfig(final CompanyGateway companyGateway) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
    }

    @Bean
    public CreateCompanyUseCase createCompanyUseCase(){
        return new DefaultCreateCompanyUseCase(companyGateway);
    }

    @Bean
    public GetCompanyByIdUseCase getCompanyByIdUseCase(){
        return new DefaultGetCompanyByIdUseCase(companyGateway);
    }

    @Bean
    public ListCompaniesUseCase listCompaniesUseCase(){
        return new DefaultListCompaniesUseCase(companyGateway);
    }

    @Bean
    public UpdateCompanyUseCase updateCompanyUseCase(){
        return new DefaultUpdateCompanyUseCase(companyGateway);
    }
}
