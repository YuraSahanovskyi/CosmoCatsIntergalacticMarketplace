package com.example.cosmocatsintergalacticmarketplace.repository.projection;

import java.time.LocalDateTime;

public interface OrderProjection {
    LocalDateTime getDate();
    String getStatus();
}
