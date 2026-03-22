package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderAnalyticListDto;
import com.example.cosmocatsintergalacticmarketplace.service.OrderAnalyticsService;
import com.example.cosmocatsintergalacticmarketplace.web.mapper.WebOrderAnalyticMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/order-analytics")
public class OrderAnalyticsController {
    private final OrderAnalyticsService orderAnalyticsService;
    private final WebOrderAnalyticMapper webOrderAnalyticMapper;

    public OrderAnalyticsController(OrderAnalyticsService orderAnalyticsService, WebOrderAnalyticMapper webOrderAnalyticMapper) {
        this.orderAnalyticsService = orderAnalyticsService;
        this.webOrderAnalyticMapper = webOrderAnalyticMapper;
    }

    @GetMapping("/{customer}")
    public ResponseEntity<OrderAnalyticListDto> getOrderAnalytics(@PathVariable String customer) {
        return ResponseEntity.ok(
                webOrderAnalyticMapper.toOrderAnalyticListDto(
                        orderAnalyticsService.getAllOrderAnalyticsByCustomerName(customer)));
    }
}
