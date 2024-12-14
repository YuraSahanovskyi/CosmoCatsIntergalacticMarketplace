package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.AbstractIT;
import com.example.cosmocatsintergalacticmarketplace.dto.product.ProductDto;
import com.example.cosmocatsintergalacticmarketplace.repository.CategoryRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.ProductRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.ProductEntity;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
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
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Product Controller IT")
public class ProductIT extends AbstractIT {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @SpyBean
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        reset(productService);
        productRepository.deleteAll();
        reset(categoryService);
        categoryRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void shouldGetAllProducts() throws Exception {
        saveProductEntity(saveCategoryEntity());
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldGetProductById() throws Exception {
        ProductEntity productEntity = saveProductEntity(saveCategoryEntity());
        mockMvc.perform(get("/api/v1/products/" + productEntity.getId()))
                .andExpect(status().isOk());
    }

    private ProductEntity saveProductEntity(CategoryEntity categoryEntity) {
        return productRepository.save(ProductEntity.builder()
                .name("Cosmic Milk")
                .description("A nutritious drink sourced from intergalactic cows, known for its energizing properties.")
                .price(BigDecimal.TEN)
                .category(categoryEntity)
                .build());
    }

    private CategoryEntity saveCategoryEntity() {
        return categoryRepository.save(
                CategoryEntity.builder()
                .name("Category 1")
                .description("Description of category 1")
                .build());
    }

    @Test
    @SneakyThrows
    void shouldSaveProduct() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        ProductDto productDto = getProductDto(categoryEntity.getId());
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }

    private ProductDto getProductDto(Long categoryId) {
        return ProductDto.builder()
                .name("Cosmic Milk")
                .description("A nutritious drink sourced from intergalactic cows, known for its energizing properties.")
                .price(BigDecimal.TEN)
                .categoryId(categoryId)
                .build();
    }

    @Test
    @SneakyThrows
    void shouldUpdateProduct() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        ProductEntity productEntity = saveProductEntity(categoryEntity);
        ProductDto productDto = getUpdatedProductDto(categoryEntity.getId());
        mockMvc.perform(put("/api/v1/products/" + productEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());
    }

    private ProductDto getUpdatedProductDto(Long categoryId) {
        return ProductDto.builder()
                .name("Cosmic Milk")
                .description("A nutritious drink sourced from intergalactic cows, known for its energizing properties.")
                .price(BigDecimal.ONE)
                .categoryId(categoryId)
                .build();
    }

    @Test
    @SneakyThrows
    void shouldDeleteProduct() throws Exception {
        ProductEntity productEntity = saveProductEntity(saveCategoryEntity());
        mockMvc.perform(delete("/api/v1/products/" + productEntity.getId()))
                .andExpect(status().isNoContent());
        assertFalse(productRepository.existsById(productEntity.getId()));
    }

    @Test
    @SneakyThrows
    void shouldGetConflict() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        saveProductEntity(categoryEntity);
        ProductDto productDto = getProductDto(categoryEntity.getId());
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isConflict());
    }

    @Test
    @SneakyThrows
    void shouldGetNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void shouldGetBadRequest() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        ProductDto productDto = getInvalidProductDto(categoryEntity.getId());
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isBadRequest());
    }

    private ProductDto getInvalidProductDto(Long categoryId) {
        return ProductDto.builder()
                .name("Regular name")
                .description("A nutritious drink sourced from intergalactic cows, known for its energizing properties.")
                .price(BigDecimal.ONE)
                .categoryId(categoryId)
                .build();
    }
}
