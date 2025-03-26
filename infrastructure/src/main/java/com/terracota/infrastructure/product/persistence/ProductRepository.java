package com.terracota.infrastructure.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {

    @Query("From Product where id = :productId and craftsman.id = :craftsmanId")
    Optional<ProductModel> findProductById(String productId, String craftsmanId);
}
