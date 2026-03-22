package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.domain.CurrencyRate;
import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.dto.product.ProductDto;
import com.example.cosmocatsintergalacticmarketplace.dto.product.ProductEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.product.ProductListDto;
import com.example.cosmocatsintergalacticmarketplace.service.CurrencyService;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
import com.example.cosmocatsintergalacticmarketplace.web.mapper.WebProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final WebProductMapper webProductMapper;
    private final CurrencyService currencyService;

    public ProductController(ProductService productService, WebProductMapper webProductMapper, CurrencyService currencyService) {
        this.productService = productService;
        this.webProductMapper = webProductMapper;
        this.currencyService = currencyService;
    }

    @GetMapping
    ResponseEntity<ProductListDto> getAllProducts(@RequestParam(required = false, name = "code") String currencyCode) {
        List<Product> products = productService.getAllProducts();
        if (currencyCode != null) {
            CurrencyRate currencyRate = currencyService.getCurrencyRate(currencyCode);
            products = products.stream()
                    .map(product -> convertPrice(product, currencyRate))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(webProductMapper.toProductListDto(products));
    }

    @GetMapping("/{id}")
    ResponseEntity<ProductEntry> getProductById(@PathVariable Long id, @RequestParam(required = false, name = "code") String currencyCode) {
        Product product = productService.getProductById(id);
        if (currencyCode != null) {
            CurrencyRate currencyRate = currencyService.getCurrencyRate(currencyCode);
            product = convertPrice(product, currencyRate);
        }
        return ResponseEntity.ok(webProductMapper.toProductEntry(product));
    }

    @PostMapping
    ResponseEntity<ProductEntry> createProduct(@RequestBody @Valid ProductDto product) {
        ProductEntry productEntry = webProductMapper.toProductEntry(productService.createProduct(webProductMapper.toProduct(product)));
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/products/" + productEntry.getId())).body(productEntry);
        //TODO: URI
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductEntry> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto product) {
        ProductEntry productEntry = webProductMapper.toProductEntry(productService.updateProduct(webProductMapper.toProductWithId(product, id)));
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/products/" + productEntry.getId())).body(productEntry);
        //TODO: URI
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    private Product convertPrice(Product product, CurrencyRate currencyRate) {
        BigDecimal convertedPrice = product.getPrice().multiply(currencyRate.getRate());
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(convertedPrice)
                .build();
    }
}
