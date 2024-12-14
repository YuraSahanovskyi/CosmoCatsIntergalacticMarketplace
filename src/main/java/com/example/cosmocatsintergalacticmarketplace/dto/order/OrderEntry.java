package com.example.cosmocatsintergalacticmarketplace.dto.order;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.time.LocalDateTime;
import java.util.Map;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class OrderEntry {
    Long id;
    String customerName;
    LocalDateTime date;
    String status;
    Map<Long, Integer> items;

}
