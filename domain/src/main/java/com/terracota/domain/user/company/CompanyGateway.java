package com.terracota.domain.user.company;

import java.util.Optional;

public interface CompanyGateway {

    Company create(Company aCompany);

    Optional<Company> findById(CompanyID anId);

    Company update(Company aCompany);

}
