package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category getCategoryById(Long id) {
        return Category.builder()
                .id(id)
                .name("Category " + id)
                .description("Description " + id)
                .build();
    }
}
