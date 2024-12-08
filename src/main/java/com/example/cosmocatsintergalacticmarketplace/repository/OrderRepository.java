package com.example.cosmocatsintergalacticmarketplace.repository;

import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
