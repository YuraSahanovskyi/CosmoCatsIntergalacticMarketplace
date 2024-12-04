package com.example.cosmocatsintergalacticmarketplace.dto.category;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CategoryListDto {
    List<CategoryEntry> categoryEntries;
}
