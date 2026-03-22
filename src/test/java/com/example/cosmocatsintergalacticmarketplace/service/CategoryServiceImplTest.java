package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import com.example.cosmocatsintergalacticmarketplace.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import com.example.cosmocatsintergalacticmarketplace.domain.Category;
import com.example.cosmocatsintergalacticmarketplace.repository.CategoryRepository;
import com.example.cosmocatsintergalacticmarketplace.service.exception.CategoryConflictException;
import com.example.cosmocatsintergalacticmarketplace.service.exception.CategoryNotFoundException;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceCategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Category Service Test")
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ServiceCategoryMapper serviceCategoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(1L)
                .name("Category 1")
                .description("Description of category 1")
                .build();

        categoryEntity = CategoryEntity.builder()
                .id(1L)
                .name("Category 1")
                .description("Description of category 1")
                .build();
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
        when(serviceCategoryMapper.toCategoryList(Collections.singletonList(categoryEntity))).thenReturn(Collections.singletonList(category));

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        verify(categoryRepository, times(1)).findAll();
        verify(serviceCategoryMapper, times(1)).toCategoryList(Collections.singletonList(categoryEntity));
    }

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(categoryEntity));
        when(serviceCategoryMapper.toCategory(categoryEntity)).thenReturn(category);

        Category foundCategory = categoryService.getCategoryById(1L);

        assertNotNull(foundCategory);
        assertEquals(category.getName(), foundCategory.getName());
        verify(categoryRepository, times(1)).findById(1L);
        verify(serviceCategoryMapper, times(1)).toCategory(categoryEntity);
    }

    @Test
    void testGetCategoryByIdNotFound() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryById(1L));

        verify(categoryRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateCategory() {
        when(categoryRepository.existsByName(category.getName())).thenReturn(false);
        when(serviceCategoryMapper.toCategoryEntity(category)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(serviceCategoryMapper.toCategory(categoryEntity)).thenReturn(category);

        Category createdCategory = categoryService.createCategory(category);

        assertNotNull(createdCategory);
        assertEquals(category.getName(), createdCategory.getName());
        verify(categoryRepository, times(1)).existsByName(category.getName());
        verify(categoryRepository, times(1)).save(categoryEntity);
        verify(serviceCategoryMapper, times(1)).toCategoryEntity(category);
        verify(serviceCategoryMapper, times(1)).toCategory(categoryEntity);
    }

    @Test
    void testCreateCategoryConflict() {
        when(categoryRepository.existsByName(category.getName())).thenReturn(true);

        assertThrows(CategoryConflictException.class, () -> categoryService.createCategory(category));

        verify(categoryRepository, times(1)).existsByName(category.getName());
        verify(categoryRepository, never()).save(any());
        verify(serviceCategoryMapper, never()).toCategoryEntity(any());
        verify(serviceCategoryMapper, never()).toCategory(any());
    }

    @Test
    void testDeleteCategoryById() {
        doNothing().when(categoryRepository).deleteById(1L);

        categoryService.deleteCategoryById(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateCategory() {
        when(categoryRepository.existsById(category.getId())).thenReturn(true);
        when(serviceCategoryMapper.toCategoryEntity(category)).thenReturn(categoryEntity);
        when(categoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
        when(serviceCategoryMapper.toCategory(categoryEntity)).thenReturn(category);

        Category updatedCategory = categoryService.updateCategory(category);

        assertNotNull(updatedCategory);
        assertEquals(category.getName(), updatedCategory.getName());
        verify(categoryRepository, times(1)).existsById(category.getId());
        verify(categoryRepository, times(1)).save(categoryEntity);
        verify(serviceCategoryMapper, times(1)).toCategoryEntity(category);
        verify(serviceCategoryMapper, times(1)).toCategory(categoryEntity);
    }

    @Test
    void testUpdateCategoryNotFound() {
        when(categoryRepository.existsById(category.getId())).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(category));

        verify(categoryRepository, times(1)).existsById(category.getId());
        verify(categoryRepository, never()).save(any());
        verify(serviceCategoryMapper, never()).toCategoryEntity(any());
        verify(serviceCategoryMapper, never()).toCategory(any());
    }
}

