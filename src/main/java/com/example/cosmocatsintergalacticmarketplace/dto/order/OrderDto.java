package com.example.cosmocatsintergalacticmarketplace.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.util.Map;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class OrderDto {
    @NotBlank(message = "Customer name is mandatory")
    String customerName;
    @NotEmpty(message = "Order must have at least one item")
    //Long is product id
    Map<Long, Integer> items;
}
