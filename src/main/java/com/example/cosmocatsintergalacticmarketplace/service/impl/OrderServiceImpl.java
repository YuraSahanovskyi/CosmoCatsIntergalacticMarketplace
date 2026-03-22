package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Order;
import com.example.cosmocatsintergalacticmarketplace.repository.OrderRepository;
import com.example.cosmocatsintergalacticmarketplace.service.OrderService;
import com.example.cosmocatsintergalacticmarketplace.service.exception.OrderNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceOrderMapper;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return serviceOrderMapper.toOrders(orderRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return serviceOrderMapper.toOrder(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Order createOrder(Order order) {
        order.setDate(LocalDateTime.now());
        order.setStatus("In progress");
        try {
            return serviceOrderMapper.toOrder(orderRepository.save(serviceOrderMapper.toOrderEntity(order)));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Order updateOrder(Order order) {
        if (!orderRepository.existsById(order.getId())) {
            throw new OrderNotFoundException(order.getId());
        }
        Order oldOrder = getOrderById(order.getId());
        order.setDate(oldOrder.getDate());
        try {
            return serviceOrderMapper.toOrder(orderRepository.save(serviceOrderMapper.toOrderEntity(order)));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }
}
