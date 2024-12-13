package com.example.cosmocatsintergalacticmarketplace.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CosmoCatEntry {
    Long id;
    String name;
    String color;
}
