package com.terracota.infrastructure.user.craftsman.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CraftsmanRepository extends JpaRepository<CraftsmanModel, String> {

    @Query("FROM Craftsman c WHERE c.user.email = :email")
    Optional<CraftsmanModel> findByUserEmail(String email);

}
