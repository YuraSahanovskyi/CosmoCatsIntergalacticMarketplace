package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class OrderNotFoundException extends RuntimeException {
    private static final String ORDER_NOT_FOUND_MESSAGE = "Order with id %s not found";
    public OrderNotFoundException(Long orderId) {
        super(String.format(ORDER_NOT_FOUND_MESSAGE, orderId));
    }
}
