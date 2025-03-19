package com.terracota.application.customer.retrieve.list;

import com.terracota.application.UseCase;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

public abstract class ListCustomerUseCase extends UseCase<SearchQuery, Pagination<ListCustomerOutput>> {
}
