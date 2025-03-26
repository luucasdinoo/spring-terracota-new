package com.terracota.application.product.retrieve.list;

import com.terracota.application.UseCase;
import com.terracota.application.product.retrieve.get.ProductOutput;
import com.terracota.domain.pagination.Pagination;

public abstract class ListByCraftsmanUseCase extends UseCase<ListByCraftsmanCommand, Pagination<ProductOutput>> {
}
