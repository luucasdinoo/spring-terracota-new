package com.terracota.customer.retrieve.list;

import com.terracota.UseCase;
import com.terracota.pagination.Pagination;
import com.terracota.pagination.SearchQuery;

public abstract class ListCustomerUseCase extends UseCase<SearchQuery, Pagination<ListCustomerOutput>> {
}
