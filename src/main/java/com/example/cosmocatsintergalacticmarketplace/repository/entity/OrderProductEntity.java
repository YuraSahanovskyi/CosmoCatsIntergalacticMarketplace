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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_product_id_seq")
    @SequenceGenerator(name = "order_product_id_seq", sequenceName = "order_product_id_seq")
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
