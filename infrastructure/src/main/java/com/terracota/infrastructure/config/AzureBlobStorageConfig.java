package com.terracota.infrastructure.config;

import com.azure.core.http.policy.HttpLogOptions;
import com.azure.spring.cloud.core.implementation.util.AzureSpringIdentifier;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.terracota.infrastructure.config.properties.AzureBlobProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty("azure.storage.terracota.blob-endpoint")
    public BlobServiceClient blobServiceClient(final AzureBlobProperties properties) {
        final String accountName = properties.getAccountName();
        final String accountKey = properties.getAccountKey();

        return new BlobServiceClientBuilder()
                .endpoint(properties.getBlobEndpoint())
                .credential(new StorageSharedKeyCredential(accountName, accountKey))
                .httpLogOptions(new HttpLogOptions().setApplicationId("terracota" + AzureSpringIdentifier.AZURE_SPRING_STORAGE_BLOB))
                .buildClient();
    }

    @Bean
    @ConfigurationProperties("azure.storage.terracota")
    public AzureBlobProperties azureBlobProperties(){
        return new AzureBlobProperties();
    }
}
