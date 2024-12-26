package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.repository.ProductRepository;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductConflictException;
import com.example.cosmocatsintergalacticmarketplace.service.exception.ProductNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceProductMapper;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Product getProductById(Long productId) {
        return serviceProductMapper.toProduct(
                productRepository.findById(productId).orElseThrow(
                        () -> new ProductNotFoundException(productId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return serviceProductMapper.toProductList(productRepository.findAll());
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Product createProduct(Product product) {
        if (productRepository.existsByNameAndCategoryId(product.getName(), product.getCategory().getId())) {
            throw new ProductConflictException(product.getName(), product.getCategory().getName());
        }
        try {
            return serviceProductMapper.toProduct(productRepository.save(serviceProductMapper.toProductEntity(product)));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        try {
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException(product.getId());
        }
        try {
            return serviceProductMapper.toProduct(
                    productRepository.save(
                            serviceProductMapper.toProductEntity(product)));
        } catch (Exception e) {
            throw new PersistenceException(e);
        }
    }

}
