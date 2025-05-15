package com.terracota.application.product.retrieve.list;

import com.terracota.application.UseCase;
import com.terracota.application.product.retrieve.get.ProductOutput;
import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

public abstract class ListProductsUseCase extends UseCase<SearchQuery, Pagination<ProductOutput>> {
}
