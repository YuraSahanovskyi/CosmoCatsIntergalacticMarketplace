package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> handleException(ProductNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
