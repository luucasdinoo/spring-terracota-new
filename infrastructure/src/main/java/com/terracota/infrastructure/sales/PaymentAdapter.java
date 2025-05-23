package com.terracota.infrastructure.sales;

import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.terracota.domain.exceptions.PaymentProcessingException;
import com.terracota.domain.product.Product;
import com.terracota.domain.product.ProductID;
import com.terracota.domain.resource.ImagePhoto;
import com.terracota.domain.sales.PaymentGateway;
import com.terracota.domain.sales.SaleID;
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
    public void process(SaleID saleId, Set<ProductID> productIds){
        try {
            List<PreferenceItemRequest> items = new ArrayList<>();
            productIds.forEach(productID -> {
                productRepository.findById(productID.getValue())
                        .ifPresent(productModel -> {
                            Product product = productModel.toDomain();
                            Optional<ImagePhoto> photo = product.getPhoto();
                            PreferenceItemRequest itemRequest =
                                    PreferenceItemRequest.builder()
                                            .id(saleId.getValue())
                                            .title(product.getName())
                                            .description(product.getDescription())
                                            .pictureUrl(photo.isPresent() ? photo.get().location() : "")
                                            .categoryId(product.getType().getValue())
                                            .quantity(2)
                                            .currencyId("BRL")
                                            .unitPrice(product.getPrice())
                                            .build();
                            items.add(itemRequest);
                        });
            });
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items).build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://www.seu-site/success")
                    .pending("https://www.seu-site/pending")
                    .failure("https://www.seu-site/failure")
                    .build();

            PreferenceRequest request = PreferenceRequest.builder()
                    .backUrls(backUrls).build();
        }catch (MPException | MPApiException e) {
            throw PaymentProcessingException.with("An error occurred while processing the payment with Mercado Pago.");
        }
    }
}
