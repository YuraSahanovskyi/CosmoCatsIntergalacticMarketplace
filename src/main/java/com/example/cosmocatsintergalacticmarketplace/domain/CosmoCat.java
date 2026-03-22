package com.example.cosmocatsintergalacticmarketplace.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CosmoCat {
    Long id;
    String name;
    String color;
}
