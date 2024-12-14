package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class ProductConflictException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Product with name %s already exists in category %s";
    public ProductConflictException(String productName, String categoryName) {
        super(String.format(DEFAULT_MESSAGE, productName, categoryName));
    }
}
