package com.terracota.domain.user.company;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.pagination.projection.CraftsmenDomainProjection;

import java.util.Optional;

public interface CompanyGateway {

    Company create(Company aCompany);

    Optional<Company> findById(CompanyID anId);

    Optional<Company> findByEmail(String email);

    Pagination<Company> list(SearchQuery aQuery);

    Pagination<CraftsmenDomainProjection> list(SearchQuery aQuery, CompanyID id);

    Company update(Company aCompany);

    String hashPassword(String password);

}
