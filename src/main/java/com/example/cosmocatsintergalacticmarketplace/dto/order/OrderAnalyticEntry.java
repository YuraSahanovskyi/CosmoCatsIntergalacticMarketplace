package com.example.cosmocatsintergalacticmarketplace.dto.order;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class OrderAnalyticEntry {
    LocalDateTime date;
    String status;
}
