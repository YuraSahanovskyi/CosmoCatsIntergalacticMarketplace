package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category getCategoryById(Long id);
}
