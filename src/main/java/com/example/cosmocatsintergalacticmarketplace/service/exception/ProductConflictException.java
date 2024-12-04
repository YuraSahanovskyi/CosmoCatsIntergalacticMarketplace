package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class ProductConflictException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Product with name %s already exists";
    public ProductConflictException(String productName) {
        super(String.format(DEFAULT_MESSAGE, productName));
    }
}
