package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class CategoryNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Category with id %s not found";
    public CategoryNotFoundException(Long categoryId) {
        super(String.format(DEFAULT_MESSAGE, categoryId));
    }
}
