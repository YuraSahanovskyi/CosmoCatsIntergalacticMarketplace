package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryDto;
import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.category.CategoryListDto;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.ServiceCategoryMapper;
import com.example.cosmocatsintergalacticmarketplace.web.mapper.WebCategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final WebCategoryMapper webCategoryMapper;

    public CategoryController(CategoryService categoryService, WebCategoryMapper webCategoryMapper) {
        this.categoryService = categoryService;
        this.webCategoryMapper = webCategoryMapper;
    }

    @GetMapping
    ResponseEntity<CategoryListDto> getAllCategories() {
        return ResponseEntity.ok(
                webCategoryMapper.toCategoryListDto(
                        categoryService.getAllCategories().stream()
                                .map(webCategoryMapper::toCategoryEntry)
                                .collect(Collectors.toList())));
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryEntry> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(webCategoryMapper.toCategoryEntry(categoryService.getCategoryById(id)));
    }

    @PostMapping
    ResponseEntity<CategoryEntry> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/v1/categories/1"))
                .body(webCategoryMapper.toCategoryEntry(
                        categoryService.createCategory(
                                webCategoryMapper.toCategory(categoryDto))));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryEntry> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.created(URI.create("http://localhost:8080/api/v1/categories/"+id))
                .body(webCategoryMapper.toCategoryEntry(
                        categoryService.updateCategory(
                                webCategoryMapper.toCategoryWithId(categoryDto, id))));
    }
}
