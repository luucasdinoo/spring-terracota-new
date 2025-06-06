package com.terracota.infrastructure.user.company;

import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CompanyAdapter implements CompanyGateway {

    private final CompanyRepository companyRepository;

    public CompanyAdapter(final CompanyRepository companyRepository) {
        this.companyRepository = Objects.requireNonNull(companyRepository);
    }

    @Override
    public Company create(final Company aCompany) {
        return save(aCompany);
    }

    @Override
    public Optional<Company> findById(CompanyID anId) {
        return this.companyRepository.findById(anId.getValue())
                .map(CompanyModel::toDomain);
    }

    @Override
    public Company update(Company aCompany) {
        return save(aCompany);
    }

    private Company save(final Company aCompany) {
        return this.companyRepository.save(CompanyModel.from(aCompany)).toDomain();
    }
}
