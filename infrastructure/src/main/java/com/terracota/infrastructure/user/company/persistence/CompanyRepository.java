package com.terracota.infrastructure.user.company.persistence;

import com.terracota.infrastructure.user.company.projection.CraftsmanProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyModel, String> {

    @Query("FROM Company c LEFT JOIN FETCH c.craftsmen")
    Page<CompanyModel> findAllWithCraftsmen(Pageable pageable);

    @Query("""
        SELECT\s
            c.id as id,
            c.user.email as email,
            c.user.role as role
        FROM Craftsman c\s
            WHERE c.company.id = :id\s
    """)
    Page<CraftsmanProjection> findCraftsmenByCompanyId(@Param("id") String id, Pageable pageable);

    @Query("FROM Company c WHERE c.owner.email = :email")
    Optional<CompanyModel> findByUserEmail(String email);

    @Query("FROM Company c LEFT JOIN FETCH c.craftsmen WHERE c.id = :id")
    Optional<CompanyModel> findByIdWithCraftsmen(@Param("id") String id);

}
