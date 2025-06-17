package com.terracota.infrastructure.services.interfaces;

import com.terracota.domain.resource.Resource;

public interface StorageService {

    String store(String name, Resource aResource);
}
