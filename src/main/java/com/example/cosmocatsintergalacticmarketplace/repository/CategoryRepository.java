package com.example.cosmocatsintergalacticmarketplace.repository;

import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
}
