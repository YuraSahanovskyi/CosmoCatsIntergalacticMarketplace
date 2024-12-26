package com.example.cosmocatsintergalacticmarketplace.service.exception;

public class CategoryConflictException extends RuntimeException {
    private static final String CATEGORY_CONFLICT_MESSAGE = "Category with name %s already exists";
    public CategoryConflictException(String categoryName) {
        super(String.format(CATEGORY_CONFLICT_MESSAGE, categoryName));
    }
}
