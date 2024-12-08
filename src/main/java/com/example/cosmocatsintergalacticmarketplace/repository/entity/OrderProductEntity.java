package com.example.cosmocatsintergalacticmarketplace.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_product")
public class OrderProductEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
