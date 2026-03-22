package com.example.cosmocatsintergalacticmarketplace.domain;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class Order {
    Long id;
    String customerName;
    LocalDateTime date;
    String status;
    Map<Product, Integer> items;
}
