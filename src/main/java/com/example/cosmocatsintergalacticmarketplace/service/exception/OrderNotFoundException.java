package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Order with id %s not found";
    public OrderNotFoundException(Long orderId) {
        super(String.format(DEFAULT_MESSAGE, orderId));
    }
}
