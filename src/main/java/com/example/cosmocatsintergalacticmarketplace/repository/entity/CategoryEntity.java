package com.example.cosmocatsintergalacticmarketplace.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_seq")
    @SequenceGenerator(name = "categories_id_seq", sequenceName = "categories_id_seq")
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
