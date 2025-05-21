package com.terracota.infrastructure.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

@Getter @Setter @Slf4j
public class StorageProperties implements InitializingBean {

    private String locationPattern;
    private String fileNamePattern;

    public StorageProperties() {}

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "StorageProperties{" +
                "locationPattern='" + locationPattern + '\'' +
                ", fileNamePattern='" + fileNamePattern + '\'' +
                '}';
    }
}
