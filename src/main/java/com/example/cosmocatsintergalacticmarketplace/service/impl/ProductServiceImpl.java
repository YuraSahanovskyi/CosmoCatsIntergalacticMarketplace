package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductAlreadyExistsException;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final List<Product> products = buildAllProductsMock();
    @Override
    public Product getProductById(Long productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElseThrow(()-> new ProductNotFoundException(productId));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Product newProduct =  Product.builder()
                .id(9L)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
        if (products.contains(newProduct)) {
            throw new ProductAlreadyExistsException(newProduct.getId());
        }
        products.add(newProduct);
        return newProduct;

    }

    private List<Product> buildAllProductsMock() {
        return new ArrayList<>(List.of(
                Product.builder()
                        .id(1L)
                        .name("Anti-Gravity Yarn Ball")
                        .description("A lightweight yarn ball that floats in mid-air. Perfect for cosmic knitting!")
                        .price(new BigDecimal("15.99"))
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("Galactic Catnip Sprays")
                        .description("Extra-strong catnip sprays from the Andromeda galaxy to make your cats go wild!")
                        .price(new BigDecimal("12.49"))
                        .build(),
                Product.builder()
                        .id(3L)
                        .name("Space Milk Treats")
                        .description("Delicious space milk treats that your cat will love. Fortified with cosmic vitamins!")
                        .price(new BigDecimal("22.99"))
                        .build(),
                Product.builder()
                        .id(4L)
                        .name("Meteorite Cat Bed")
                        .description("Cozy cat bed made from real meteorite materials. Perfect for the intergalactic traveler!")
                        .price(new BigDecimal("79.99"))
                        .build(),
                Product.builder()
                        .id(5L)
                        .name("Stellar Fish Flakes")
                        .description("Tasty fish flakes harvested from the rings of Saturn. A treat fit for a space feline!")
                        .price(new BigDecimal("5.99"))
                        .build(),
                Product.builder()
                        .id(6L)
                        .name("Feline Starship Toy")
                        .description("A plush starship toy that lights up and makes sounds when your cat plays with it.")
                        .price(new BigDecimal("34.99"))
                        .build(),
                Product.builder()
                        .id(7L)
                        .name("Intergalactic Grooming Kit")
                        .description("Grooming kit designed for cosmic cats, featuring tools made from stardust.")
                        .price(new BigDecimal("19.99"))
                        .build(),
                Product.builder()
                        .id(8L)
                        .name("Cosmic Litter Box")
                        .description("A high-tech litter box that automatically cleans itself. No more stinky spaceships!")
                        .price(new BigDecimal("149.99"))
                        .build()
        ));
    }

}
