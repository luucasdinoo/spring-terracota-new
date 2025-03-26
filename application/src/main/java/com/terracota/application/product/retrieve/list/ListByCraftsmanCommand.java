package com.terracota.application.product.retrieve.list;

import com.terracota.domain.pagination.SearchQuery;

public record ListByCraftsmanCommand(String craftsmanId, SearchQuery aQuery){
    public static ListByCraftsmanCommand with(final String craftsmanId, final SearchQuery aQuery){
        return new ListByCraftsmanCommand(craftsmanId, aQuery);
    }
}
