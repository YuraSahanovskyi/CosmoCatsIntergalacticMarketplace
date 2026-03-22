package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.OrderAnalytic;
import com.example.cosmocatsintergalacticmarketplace.repository.OrderRepository;
import com.example.cosmocatsintergalacticmarketplace.service.OrderAnalyticsService;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceOrderAnalyticMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderAnalyticsServiceImpl implements OrderAnalyticsService {
    private final OrderRepository orderRepository;
    private final ServiceOrderAnalyticMapper serviceOrderAnalyticMapper;

    public OrderAnalyticsServiceImpl(OrderRepository orderRepository, ServiceOrderAnalyticMapper serviceOrderAnalyticMapper) {
        this.orderRepository = orderRepository;
        this.serviceOrderAnalyticMapper = serviceOrderAnalyticMapper;
    }

    @Override
    public List<OrderAnalytic> getAllOrderAnalyticsByCustomerName(String customerName) {
        return serviceOrderAnalyticMapper.toOrderAnalyticList(
                orderRepository.findAllByCustomerName(customerName)
        );
    }
}
