package com.terracota.domain.user.company;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;

import java.util.Optional;

public interface CompanyGateway {

    Company create(Company aCustomer);

    Optional<Company> findById(CompanyID anId);

    Company update(Company aCustomer);

    Pagination<Company> list(SearchQuery aQuery);

}
