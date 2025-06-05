package com.terracota.application.company.retrieve.list;

import com.terracota.application.UseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

public abstract class ListCompaniesUseCase extends UseCase<SearchQuery, Pagination<ListCompaniesOutput>> {
}
