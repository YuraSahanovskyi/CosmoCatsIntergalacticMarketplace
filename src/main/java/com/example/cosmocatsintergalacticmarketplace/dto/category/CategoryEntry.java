package com.example.cosmocatsintergalacticmarketplace.dto.category;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CategoryEntry {
    Long id;
    String name;
    String description;
}
