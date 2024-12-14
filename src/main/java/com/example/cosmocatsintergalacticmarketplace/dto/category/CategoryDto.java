package com.example.cosmocatsintergalacticmarketplace.dto.category;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder=true)
@Jacksonized
public class CategoryDto {
    String name;
    String description;
}
