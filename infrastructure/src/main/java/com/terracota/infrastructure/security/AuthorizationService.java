package com.terracota.infrastructure.security;

import com.terracota.domain.exceptions.EntityNotFoundException;
import com.terracota.infrastructure.user.company.persistence.CompanyModel;
import com.terracota.infrastructure.user.company.persistence.CompanyRepository;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanRepository;
import com.terracota.infrastructure.user.customer.persistence.CustomerModel;
import com.terracota.infrastructure.user.customer.persistence.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthorizationService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final CraftsmanRepository craftsmanRepository;
    private final CompanyRepository companyRepository;

    public AuthorizationService(
            final CustomerRepository customerRepository,
            final CraftsmanRepository craftsmanRepository,
            final CompanyRepository companyRepository
    ) {
        this.customerRepository = Objects.requireNonNull(customerRepository);
        this.craftsmanRepository = Objects.requireNonNull(craftsmanRepository);
        this.companyRepository = Objects.requireNonNull(companyRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundException {
        return this.customerRepository.findByUserEmail(email)
                .map(CustomerModel::getUser)
                .or(() -> this.craftsmanRepository.findByUserEmail(email)
                        .map(CraftsmanModel::getUser))
                .or(() -> this.companyRepository.findByUserEmail(email)
                        .map(CompanyModel::getOwner))
                .orElseThrow(() -> EntityNotFoundException.with("User not found"));
    }
}
