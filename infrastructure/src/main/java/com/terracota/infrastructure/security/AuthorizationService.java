package com.terracota.infrastructure.security;

import com.terracota.domain.exceptions.EntityNotFoundException;
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

    public AuthorizationService(
            final CustomerRepository customerRepository,
            final CraftsmanRepository craftsmanRepository
    ) {
        this.customerRepository = Objects.requireNonNull(customerRepository);
        this.craftsmanRepository = Objects.requireNonNull(craftsmanRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundException {
        return customerRepository.findByUserEmail(email)
                .map(CustomerModel::getUser)
                .or(() -> craftsmanRepository.findByUserEmail(email)
                        .map(CraftsmanModel::getUser))
                .orElseThrow(() -> EntityNotFoundException.with("User not found"));
    }
}
