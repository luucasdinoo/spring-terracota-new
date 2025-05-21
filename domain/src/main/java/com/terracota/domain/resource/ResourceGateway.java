package com.terracota.domain.resource;

import com.terracota.domain.Identifier;

public interface ResourceGateway {

    <T extends Identifier> ImagePhoto storeImage(T anId, MediaResource aResource);

    <T extends Identifier> void clearResources(T anId);
}
