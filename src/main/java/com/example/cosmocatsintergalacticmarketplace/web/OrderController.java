package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderDto;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderListDto;
import com.example.cosmocatsintergalacticmarketplace.service.OrderService;
import com.example.cosmocatsintergalacticmarketplace.web.mapper.WebOrderMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final WebOrderMapper webOrderMapper;

    public OrderController(OrderService orderService, WebOrderMapper webOrderMapper) {
        this.orderService = orderService;
        this.webOrderMapper = webOrderMapper;
    }

    @GetMapping
    ResponseEntity<OrderListDto> getAllOrders() {
        return ResponseEntity.ok(webOrderMapper.toOrderListDto(orderService.getAllOrders()));
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderEntry> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(webOrderMapper.toOrderEntry(orderService.getOrderById(id)));
    }

    @PostMapping
    ResponseEntity<OrderEntry> createOrder(@RequestBody @Valid OrderDto orderDto) {
        OrderEntry orderEntry = webOrderMapper.toOrderEntry(orderService.createOrder(webOrderMapper.toOrder(orderDto)));
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/orders/" + orderEntry.getId())).body(orderEntry);
    }
    @PutMapping("/{id}")
    ResponseEntity<OrderEntry> updateOrder(@PathVariable("id") Long id, @RequestBody @Valid OrderDto orderDto) {
        OrderEntry orderEntry = webOrderMapper.toOrderEntry(
                orderService.updateOrder(
                        webOrderMapper.toOrderWithId(orderDto, id)));
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/orders/" + orderEntry.getId())).body(orderEntry);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
