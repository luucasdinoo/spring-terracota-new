package com.terracota.infrastructure.user.craftsman.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CraftsmanRepository extends JpaRepository<CraftsmanModel, String> {

    Optional<CraftsmanModel> findByEmail(String email);

}
