package com.terracota.infrastructure.config.usecase;

import com.terracota.application.craftsman.create.CreateCraftsmanUseCase;
import com.terracota.application.craftsman.create.DefaultCreateCraftsmanUseCase;
import com.terracota.application.craftsman.delete.DefaultDeleteCraftsmanUseCase;
import com.terracota.application.craftsman.delete.DeleteCraftsmanUseCase;
import com.terracota.application.craftsman.retrieve.get.DefaultGetCraftsmanByEmailUseCase;
import com.terracota.application.craftsman.retrieve.get.DefaultGetCraftsmanByIdUseCase;
import com.terracota.application.craftsman.retrieve.get.GetCraftsmanByEmailUseCase;
import com.terracota.application.craftsman.retrieve.get.GetCraftsmanByIdUseCase;
import com.terracota.application.craftsman.retrieve.list.DefaultListCraftsmenUseCase;
import com.terracota.application.craftsman.retrieve.list.ListCraftsmenUseCase;
import com.terracota.application.craftsman.update.DefaultUpdateCraftsmanUseCase;
import com.terracota.application.craftsman.update.UpdateCraftsmanUseCase;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CraftsmanUseCaseConfig {

    private final CraftsmanGateway craftsmanGateway;

    public CraftsmanUseCaseConfig(final CraftsmanGateway craftsmanGateway) {
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Bean
    public CreateCraftsmanUseCase createCraftsmanUseCase(){
        return new DefaultCreateCraftsmanUseCase(craftsmanGateway);
    }

    @Bean
    public UpdateCraftsmanUseCase updateCraftsmanUseCase(){
        return new DefaultUpdateCraftsmanUseCase(craftsmanGateway);
    }

    @Bean
    public DeleteCraftsmanUseCase deleteCraftsmanUseCase(){
        return new DefaultDeleteCraftsmanUseCase(craftsmanGateway);
    }

    @Bean
    public GetCraftsmanByIdUseCase getCraftsmanByIdUseCase(){
        return new DefaultGetCraftsmanByIdUseCase(craftsmanGateway);
    }

    @Bean
    public GetCraftsmanByEmailUseCase getCraftsmanByEmailUseCase(){
        return new DefaultGetCraftsmanByEmailUseCase(craftsmanGateway);
    }

    @Bean
    public ListCraftsmenUseCase listCraftsmenUseCase(){
        return new DefaultListCraftsmenUseCase(craftsmanGateway);
    }
}
