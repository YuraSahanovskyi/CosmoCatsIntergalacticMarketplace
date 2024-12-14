package com.example.cosmocatsintergalacticmarketplace.repository;

import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.projection.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderProjection> findAllByCustomerName(String customerName);
}
