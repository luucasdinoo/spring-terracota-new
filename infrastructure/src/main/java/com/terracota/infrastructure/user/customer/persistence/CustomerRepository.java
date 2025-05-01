package com.terracota.infrastructure.user.customer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

    Optional<CustomerModel> findByEmail(String email);
}
