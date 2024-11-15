package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductAlreadyExistsException;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ProductServiceImpl.class})
@DirtiesContext
@DisplayName("Product Service Test")
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void getAllProducts() {
        assertFalse(productService.getAllProducts().isEmpty());
    }

    @Test
    void getProductById() {
        assertNotNull(productService.getProductById(1L));
        assertDoesNotThrow(() -> productService.getProductById(1L));
    }

    @Test
    void throwsNotFound() {
        productService.deleteProduct(2L);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(2L));
    }

    @Test
    void addProduct() {
        productService.deleteProduct(9L);
        var pb = Product.builder()
                .name("Cosmic Milk")
                .description("A nutritious drink sourced from intergalactic cows, known for its energizing properties.")
                .price(BigDecimal.valueOf(5.49));

        Product product = pb.build();
        productService.createProduct(product);
        Product expected = pb.id(9L).build();
        assertEquals(expected, productService.getProductById(9L));
    }

    @Test
    void alreadyExistThrowsException() {
        Product product = Product.builder()
                .name("Anti-Gravity Yarn Ball")
                .description("A lightweight yarn ball that floats in mid-air. Perfect for cosmic knitting!")
                .price(new BigDecimal("15.99"))
                .build();
        productService.createProduct(product);
        assertThrows(ProductAlreadyExistsException.class, () -> productService.createProduct(product));
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(3L);
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(3L));
    }
}
