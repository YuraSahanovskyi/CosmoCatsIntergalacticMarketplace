package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.AbstractIT;
import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryDto;
import com.example.cosmocatsintergalacticmarketplace.repository.CategoryRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Category Controller IT")
public class CategoryIT extends AbstractIT {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        reset(categoryService);
        categoryRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void shouldGetAllCategories() throws Exception {
        saveCategoryEntity();
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldGetCategoryByID() throws Exception {
        CategoryEntity category = saveCategoryEntity();
        mockMvc.perform(get("/api/v1/categories/" + category.getId()))
                .andExpect(status().isOk());
    }

    private CategoryEntity saveCategoryEntity() {
        return categoryRepository.save(getCategoryEntity());
    }

    @Test
    @SneakyThrows
    void shouldSaveCategory() throws Exception {
        CategoryDto categoryDto = getCategoryDto();
        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isCreated());
    }

    private CategoryDto getCategoryDto() {
        return CategoryDto.builder()
                .name("Category 1")
                .description("Description of category 1")
                .build();
    }

    @Test
    @SneakyThrows
    void shouldUpdateCategory() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        CategoryDto categoryDto = getUpdatedCategoryDto();
        mockMvc.perform(put("/api/v1/categories/" + categoryEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isCreated());
    }

    private CategoryDto getUpdatedCategoryDto() {
        return CategoryDto.builder()
                .name("Updated Category 1")
                .description("Description of category 1")
                .build();
    }

    private CategoryEntity getCategoryEntity() {
        return CategoryEntity.builder()
                .name("Category 1")
                .description("Description of category 1")
                .build();
    }

    @Test
    @SneakyThrows
    void shouldDeleteCategory() throws Exception {
        CategoryEntity category = saveCategoryEntity();
        mockMvc.perform(delete("/api/v1/categories/" + category.getId()))
                .andExpect(status().isNoContent());
        assertFalse(categoryRepository.existsById(category.getId()));
    }

    @Test
    @SneakyThrows
    void shouldGetConflict() throws Exception {
        saveCategoryEntity();
        CategoryDto categoryDto = getCategoryDto();
        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(status().isConflict());
    }

    @Test
    @SneakyThrows
    void shouldGetNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/categories/1"))
                .andExpect(status().isNotFound());
    }

}
