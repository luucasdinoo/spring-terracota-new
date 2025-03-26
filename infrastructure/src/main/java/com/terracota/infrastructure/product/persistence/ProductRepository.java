package com.terracota.infrastructure.product.persistence;

import com.terracota.infrastructure.user.craftsman.persistence.CraftsmanModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    @Query("From Product where id = :productId and craftsman.id = :craftsmanId")
    Optional<ProductModel> findProductById(String productId, String craftsmanId);

    Page<ProductModel> findProductsByCraftsman(CraftsmanModel craftsman, Pageable pageable);
}
