package com.terracota.infrastructure.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Getter @Setter @Slf4j
public class AzureBlobProperties implements InitializingBean {

    private String accountName;
    private String accountKey;
    private String blobEndpoint;
    private String photosContainerName;

    public AzureBlobProperties(){}

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "AzureBlobProperties{" +
                "accountName='" + accountName + '\'' +
                ", accountKey='" + accountKey + '\'' +
                ", accountKey='" + blobEndpoint + '\'' +
                ", accountKey='" + photosContainerName + '\'' +
                '}';
    }
}
