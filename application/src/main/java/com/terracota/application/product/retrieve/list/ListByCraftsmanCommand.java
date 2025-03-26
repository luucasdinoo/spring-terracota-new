package com.terracota.application.product.retrieve.list;

import com.terracota.domain.pagination.SearchQuery;

public record ListByCraftsmanCommand(String craftsmanId, SearchQuery aQuery){
}
