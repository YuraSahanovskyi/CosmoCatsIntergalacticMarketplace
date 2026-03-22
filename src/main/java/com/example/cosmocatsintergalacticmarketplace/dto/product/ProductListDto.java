package com.example.cosmocatsintergalacticmarketplace.dto.product;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class ProductListDto {
    List<ProductEntry> productEntries;
}
