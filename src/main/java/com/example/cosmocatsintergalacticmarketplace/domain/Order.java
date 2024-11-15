package com.example.cosmocatsintergalacticmarketplace.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder
public class Order {
    Long id;
    String customerName;
    LocalDateTime date;
    String status;
    Map<Product, Integer> items;
}
