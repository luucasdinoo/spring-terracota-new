package com.terracota.application.company.retrieve.list;

import com.terracota.domain.pagination.SearchQuery;

public record ListCraftsmenCompanyCommand(
        SearchQuery searchQuery,
        String id
) {
    public static ListCraftsmenCompanyCommand with(final SearchQuery aQuery,  String id) {
        return new ListCraftsmenCompanyCommand(aQuery, id);
    }
}
