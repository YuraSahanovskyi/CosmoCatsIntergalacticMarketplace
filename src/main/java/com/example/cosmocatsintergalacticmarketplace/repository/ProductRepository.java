package com.example.cosmocatsintergalacticmarketplace.repository;

import com.example.cosmocatsintergalacticmarketplace.repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
}
