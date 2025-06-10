package com.terracota.application.company.retrieve.list;

import com.terracota.application.UseCase;
import com.terracota.application.craftsman.retrieve.list.ListCraftsmenOutput;
import com.terracota.domain.pagination.Pagination;

public abstract class ListCraftsmenCompanyUseCase extends UseCase<ListCraftsmenCompanyCommand, Pagination<ListCraftsmenOutput>> {
}
