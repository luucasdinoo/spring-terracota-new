package com.terracota.infrastructure.user.company;

import com.terracota.domain.pagination.Pagination;
import com.terracota.domain.pagination.SearchQuery;
import com.terracota.domain.user.company.Company;
import com.terracota.domain.user.company.CompanyGateway;
import com.terracota.domain.user.company.CompanyID;
import com.terracota.infrastructure.user.company.persistence.CompanyModel;
import com.terracota.infrastructure.user.company.persistence.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CompanyAdapter implements CompanyGateway {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;


    public CompanyAdapter(final CompanyRepository companyRepository, final PasswordEncoder passwordEncoder) {
        this.companyRepository = Objects.requireNonNull(companyRepository);
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    }

    @Override
    public Company create(final Company aCompany) {
        return save(aCompany);
    }

    @Override
    public Optional<Company> findById(CompanyID anId) {
        return this.companyRepository.findByIdWithCraftsmen(anId.getValue())
                .map(CompanyModel::toDomain);
    }

    @Override
    public Pagination<Company> list(final SearchQuery aQuery) {
        PageRequest page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        Page<CompanyModel> pageResult = this.companyRepository.findAllWithCraftsmen(page);
        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CompanyModel::toDomain).toList()
        );
    }

    @Transactional
    @Override
    public Company update(final Company aCompany) {
        return save(aCompany);
    }

    @Override
    public String hashPassword(final String password) {
        return passwordEncoder.encode(password);
    }

    private Company save(final Company aCompany) {
        return this.companyRepository.save(CompanyModel.from(aCompany)).toDomain();
    }
}
