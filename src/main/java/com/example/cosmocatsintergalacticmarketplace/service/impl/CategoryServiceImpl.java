package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.repository.CategoryRepository;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import com.example.cosmocatsintergalacticmarketplace.service.exception.CategoryConflictException;
import com.example.cosmocatsintergalacticmarketplace.service.exception.CategoryNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ServiceCategoryMapper serviceCategoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ServiceCategoryMapper serviceCategoryMapper) {
        this.categoryRepository = categoryRepository;
        this.serviceCategoryMapper = serviceCategoryMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {
        return serviceCategoryMapper.toCategoryList(categoryRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return serviceCategoryMapper.toCategory(
                categoryRepository.findById(id).orElseThrow(
                        () -> new CategoryNotFoundException(id)));
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Category createCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryConflictException(category.getName());
        }
        return serviceCategoryMapper.toCategory(
                categoryRepository.save(
                        serviceCategoryMapper.toCategoryEntity(category)));
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Category updateCategory(Category category) {
        if(!categoryRepository.existsById(category.getId())) {
            throw new CategoryNotFoundException(category.getId());
        }
        return serviceCategoryMapper.toCategory(
                categoryRepository.save(serviceCategoryMapper.toCategoryEntity(category)));
    }
}
