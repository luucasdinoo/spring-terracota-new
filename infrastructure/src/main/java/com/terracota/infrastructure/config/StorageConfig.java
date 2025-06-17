package com.terracota.infrastructure.config;

import com.azure.storage.blob.BlobServiceClient;
import com.terracota.infrastructure.config.properties.AzureBlobProperties;
import com.terracota.infrastructure.config.properties.StorageProperties;
import com.terracota.infrastructure.services.AzureStorageService;
import com.terracota.infrastructure.services.interfaces.StorageService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    private final BlobServiceClient blobServiceClient;
    private final AzureBlobProperties azureBlobProperties;

    public StorageConfig(
            final BlobServiceClient blobServiceClient,
            final AzureBlobProperties azureBlobProperties
    ) {
        this.blobServiceClient = blobServiceClient;
        this.azureBlobProperties = azureBlobProperties;
    }

    @Bean
    @ConfigurationProperties("storage.terracota")
    public StorageProperties storageProperties(){
        return new StorageProperties();
    }

    @Bean
    public StorageService azureBlobStorageService(){
        return new AzureStorageService(blobServiceClient, azureBlobProperties);
    }
}
