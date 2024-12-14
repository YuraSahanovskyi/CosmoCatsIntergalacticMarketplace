package com.example.cosmocatsintergalacticmarketplace.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderAnalytic {
    LocalDateTime date;
    String status;
}
