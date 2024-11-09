package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Product with id %s already exists";
    public ProductAlreadyExistsException(Long customerId) {
        super(String.format(DEFAULT_MESSAGE, customerId));
    }
}
