package com.terracota.infrastructure.config.usecase;

import com.terracota.application.product.create.CreateProductUseCase;
import com.terracota.application.product.create.DefaultCreateProductUseCase;
import com.terracota.application.product.delete.DefaultDeleteProductUseCase;
import com.terracota.application.product.delete.DeleteProductUseCase;
import com.terracota.application.product.retrieve.get.DefaultGetProductByIdUseCase;
import com.terracota.application.product.retrieve.get.GetProductByIdUseCase;
import com.terracota.application.product.retrieve.list.DefaultListByCraftsmanUseCase;
import com.terracota.application.product.retrieve.list.ListByCraftsmanUseCase;
import com.terracota.application.product.update.DefaultUpdateProductUseCase;
import com.terracota.application.product.update.UpdateProductUseCase;
import com.terracota.domain.product.ProductGateway;
import com.terracota.domain.user.craftsman.CraftsmanGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class ProductUseCaseConfig {

    private final ProductGateway productGateway;
    private final CraftsmanGateway craftsmanGateway;

    public ProductUseCaseConfig(
            final ProductGateway productGateway,
            final CraftsmanGateway craftsmanGateway
    ) {
        this.productGateway = Objects.requireNonNull(productGateway);
        this.craftsmanGateway = Objects.requireNonNull(craftsmanGateway);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(){
        return new DefaultCreateProductUseCase(productGateway, craftsmanGateway);
    }

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase(){
        return new DefaultGetProductByIdUseCase(productGateway);
    }

    @Bean
    public ListByCraftsmanUseCase listByCraftsmanUseCase(){
        return new DefaultListByCraftsmanUseCase(productGateway, craftsmanGateway);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(){
        return new DefaultUpdateProductUseCase(productGateway);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(){
        return new DefaultDeleteProductUseCase(productGateway);
    }
}
