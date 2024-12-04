package com.example.cosmocatsintergalacticmarketplace.dto.product;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class ProductEntry {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Long categoryId;
}
