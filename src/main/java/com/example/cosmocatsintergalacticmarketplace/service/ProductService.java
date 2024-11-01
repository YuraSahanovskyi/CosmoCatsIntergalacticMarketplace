package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
}
