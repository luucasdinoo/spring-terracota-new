package com.terracota.infrastructure.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.terracota.domain.resource.Resource;
import com.terracota.infrastructure.config.properties.AzureBlobProperties;
import com.terracota.infrastructure.services.interfaces.StorageService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Objects;

public class AzureStorageService implements StorageService {

    private final BlobServiceClient blobClient;
    private final AzureBlobProperties properties;

    public AzureStorageService(
            final BlobServiceClient blobClient,
            final AzureBlobProperties properties
    ) {
        this.blobClient = Objects.requireNonNull(blobClient);
        this.properties = properties;
    }

    @Override
    public String store(final String filePath, final Resource aResource) {
        BlobContainerClient containerCli = this.blobClient.getBlobContainerClient(properties.getPhotosContainerName());

        BlobClient blobCli = containerCli.getBlobClient(filePath);

        InputStream inputStream = new ByteArrayInputStream(aResource.content());

        BlobHttpHeaders headers = new BlobHttpHeaders()
                .setContentType(aResource.contentType());

        blobCli.upload(inputStream, aResource.content().length,true);
        blobCli.setHttpHeaders(headers);
        return blobCli.getBlobUrl();
    }
}
