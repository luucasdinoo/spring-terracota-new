package com.terracota.infrastructure.user.craftsman.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftsmanRepository extends JpaRepository<CraftsmanModel, String> {
}
