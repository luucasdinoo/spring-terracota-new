package com.terracota.infrastructure.sales;

import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.terracota.domain.exceptions.PaymentProcessingException;
import com.terracota.domain.product.Product;
import com.terracota.domain.resource.ImagePhoto;
import com.terracota.domain.sales.PaymentGateway;
import com.terracota.domain.sales.SaleItem;
import com.terracota.infrastructure.product.persistence.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PaymentAdapter implements PaymentGateway {

    private final ProductRepository productRepository;

    public PaymentAdapter(final ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public String generateLink(Set<SaleItem> saleItems){
        try {
            PreferenceClient client = new PreferenceClient();
            List<PreferenceItemRequest> items = new ArrayList<>();
            saleItems.forEach(saleItem -> {
                productRepository.findById(saleItem.productId().getValue())
                        .ifPresent(productModel -> {
                            Product product = productModel.toDomain();
                            Optional<ImagePhoto> photo = product.getPhoto();
                            PreferenceItemRequest itemRequest =
                                    PreferenceItemRequest.builder()
                                            .id(product.getId().getValue())
                                            .title(product.getName())
                                            .description(product.getDescription())
                                            .pictureUrl(photo.isPresent() ? photo.get().location() : "")
                                            .categoryId(product.getType().getValue())
                                            .quantity(saleItem.quantity())
                                            .currencyId("BRL")
                                            .unitPrice(product.getPrice())
                                            .build();
                            items.add(itemRequest);
                        });
            });
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success("https://test.com/success")
                                    .failure("https://test.com/failure")
                                    .pending("https://test.com/pending")
                                    .build())
                    .build();

            Preference preference = client.create(preferenceRequest);
            return preference.getSandboxInitPoint();

        }catch (MPException | MPApiException e) {
            throw PaymentProcessingException.with("An error occurred while processing the payment with Mercado Pago.");
        }
    }

    @Override
    public void process() {

    }
}
