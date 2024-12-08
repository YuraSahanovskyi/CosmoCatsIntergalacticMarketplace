package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Order;
import com.example.cosmocatsintergalacticmarketplace.repository.OrderRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderEntity;
import com.example.cosmocatsintergalacticmarketplace.service.OrderService;
import com.example.cosmocatsintergalacticmarketplace.service.exception.OrderNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceOrderMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ServiceOrderMapper serviceOrderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ServiceOrderMapper serviceOrderMapper) {
        this.orderRepository = orderRepository;
        this.serviceOrderMapper = serviceOrderMapper;
    }

    @Override
    public List<Order> getAllOrders() {
        return serviceOrderMapper.toOrders(orderRepository.findAll());
    }

    @Override
    public Order getOrderById(Long id) {
        return serviceOrderMapper.toOrder(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        order.setDate(LocalDateTime.now());
        order.setStatus("In progress");
        return serviceOrderMapper.toOrder(orderRepository.save(serviceOrderMapper.toOrderEntity(order)));
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        if (!orderRepository.existsById(order.getId())) {
            throw new OrderNotFoundException(order.getId());
        }
        Order oldOrder = getOrderById(order.getId());
        order.setDate(oldOrder.getDate());
        return serviceOrderMapper.toOrder(orderRepository.save(serviceOrderMapper.toOrderEntity(order)));
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
