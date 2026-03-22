package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    void deleteCategoryById(Long id);
    Category updateCategory(Category category);
}
