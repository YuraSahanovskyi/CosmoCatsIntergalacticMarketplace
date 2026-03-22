package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class ProductConflictException extends RuntimeException {
    private static final String PRODUCT_CONFLICT_MESSAGE = "Product with name %s already exists in category %s";
    public ProductConflictException(String productName, String categoryName) {
        super(String.format(PRODUCT_CONFLICT_MESSAGE, productName, categoryName));
    }
}
