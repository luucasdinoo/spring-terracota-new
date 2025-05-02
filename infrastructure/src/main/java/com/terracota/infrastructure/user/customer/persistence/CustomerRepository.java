package com.terracota.infrastructure.user.customer.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

    @Query("FROM Customer c WHERE c.user.email = :email")
    Optional<CustomerModel> findByUserEmail(String email);

}
