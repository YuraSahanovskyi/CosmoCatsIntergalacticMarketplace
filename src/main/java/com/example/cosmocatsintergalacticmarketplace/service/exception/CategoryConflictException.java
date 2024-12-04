package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class CategoryConflictException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Category with name %s already exists";
    public CategoryConflictException(String categoryName) {
        super(String.format(DEFAULT_MESSAGE, categoryName));
    }
}
