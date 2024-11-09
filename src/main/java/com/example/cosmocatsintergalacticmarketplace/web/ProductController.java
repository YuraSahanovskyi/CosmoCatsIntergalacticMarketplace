package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.dto.ProductDto;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductListDto;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    ResponseEntity<ProductListDto> getAllProducts() {
        return ResponseEntity.ok(productMapper.toProductListDto(productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductEntry> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productMapper.toProductEntry(productService.getProductById(id)));
    }

    @PostMapping
    ResponseEntity<ProductEntry> createProduct(@RequestBody @Valid ProductDto product) {
        ProductEntry productEntry = productMapper.toProductEntry(productService.createProduct(productMapper.toProduct(product)));
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/products/" + productEntry.getId())).body(productEntry);
        //TODO: URI
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductEntry> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto product) {
        ProductEntry productEntry = productMapper.toProductEntry(productService.updateProduct(id, productMapper.toProduct(product)));
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/products/" + productEntry.getId())).body(productEntry);
        //TODO: URI
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
