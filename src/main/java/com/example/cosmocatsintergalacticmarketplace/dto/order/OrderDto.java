package com.example.cosmocatsintergalacticmarketplace.dto.order;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.util.Map;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class OrderDto {
    @NotBlank(message = "Customer name is mandatory")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    String customerName;
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Nullable
    String status;
    @NotEmpty(message = "Order must have at least one item")
    //Long is product id
    Map<Long, Integer> items;
}
