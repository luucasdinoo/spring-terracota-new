package com.terracota.infrastructure.config.usecase;

import com.terracota.application.company.create.CreateCompanyUseCase;
import com.terracota.application.company.create.DefaultCreateCompanyUseCase;
import com.terracota.application.company.management.add.AddCraftsmanUseCase;
import com.terracota.application.company.management.add.DefaultAddCraftsmanUseCase;
import com.terracota.application.company.management.remove.DefaultRemoveCraftsmanUseCase;
import com.terracota.application.company.management.remove.RemoveCraftsmanUseCase;
import com.terracota.application.company.retrieve.get.DefaultGetCompanyByIdUseCase;
import com.terracota.application.company.retrieve.get.GetCompanyByIdUseCase;
import com.terracota.application.company.retrieve.list.DefaultListCraftsmenCompanyUseCase;
import com.terracota.application.company.retrieve.list.ListCraftsmenCompanyUseCase;
import com.terracota.application.company.update.DefaultUpdateCompanyUseCase;
import com.terracota.application.company.update.UpdateCompanyUseCase;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CompanyUseCaseConfig {

    private final CompanyGateway companyGateway;
    private final CraftsmanGateway craftsmanGateway;

    public CompanyUseCaseConfig(
            final CompanyGateway companyGateway,
            final CraftsmanGateway craftsmanGateway
    ) {
        this.companyGateway = Objects.requireNonNull(companyGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
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
    public UpdateCompanyUseCase updateCompanyUseCase(){
        return new DefaultUpdateCompanyUseCase(companyGateway);
    }

    @Bean
    public AddCraftsmanUseCase addCraftsmanUseCase(){
        return new DefaultAddCraftsmanUseCase(companyGateway, craftsmanGateway);
    }

    @Bean
    public RemoveCraftsmanUseCase removeCraftsmanUseCase(){
        return new DefaultRemoveCraftsmanUseCase(craftsmanGateway);
    }

    @Bean
    public ListCraftsmenCompanyUseCase listCraftsmenCompanyUseCase(){
        return new DefaultListCraftsmenCompanyUseCase(companyGateway);
    }
}
