package com.terracota.application.craftsman.retrieve.list;

import com.terracota.application.UseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

public abstract class ListCraftsmenUseCase extends UseCase<SearchQuery, Pagination<ListCraftsmenOutput>> {
}
