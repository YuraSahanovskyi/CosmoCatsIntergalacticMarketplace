package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.repository.ProductRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.ProductEntity;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductConflictException;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ServiceProductMapper serviceProductMapper;


    public ProductServiceImpl(ProductRepository productRepository, ServiceProductMapper serviceProductMapper) {
        this.productRepository = productRepository;
        this.serviceProductMapper = serviceProductMapper;
    }

    @Override
    public Product getProductById(Long productId) {
        return serviceProductMapper.toProduct(
                productRepository.findById(productId).orElseThrow(
                        () -> new ProductNotFoundException(productId)));
    }

    @Override
    public List<Product> getAllProducts() {
        return serviceProductMapper.toProductList(productRepository.findAll());
    }

    @Override
    public Product createProduct(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new ProductConflictException(product.getName());
        }
        ProductEntity productEntity = serviceProductMapper.toProductEntity(product);
        productEntity = productRepository.save(productEntity);
        return serviceProductMapper.toProduct(productEntity);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException(product.getId());
        }
        return serviceProductMapper.toProduct(
                productRepository.save(
                        serviceProductMapper.toProductEntity(product)));
    }

}
