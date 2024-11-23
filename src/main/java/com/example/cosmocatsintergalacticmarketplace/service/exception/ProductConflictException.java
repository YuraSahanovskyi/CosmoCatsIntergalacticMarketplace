package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class ProductConflictException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Product with id %s already exists";
    public ProductConflictException(Long customerId) {
        super(String.format(DEFAULT_MESSAGE, customerId));
    }
}
