package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.domain.OrderAnalytic;
import java.util.List;

public interface OrderAnalyticsService {
    List<OrderAnalytic> getAllOrderAnalyticsByCustomerName(String customerName);
}
