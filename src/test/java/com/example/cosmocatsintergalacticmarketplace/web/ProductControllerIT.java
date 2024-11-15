package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final ProductDto PRODUCT_DTO =ProductDto.builder()
            .name("Space")
            .description("Test")
            .price(BigDecimal.valueOf(5.99))
            .build();

    private static final ProductDto INVALID_PRODUCT_DTO = ProductDto.builder()
            .name("Regular name")
            .description("Test")
            .price(BigDecimal.valueOf(5.99))
            .build();

    @Test
    @SneakyThrows
    void getProducts() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getProductById() throws Exception {
        mockMvc.perform(get("/api/v1/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
    @Test
    @SneakyThrows
    void createProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/products/9"));

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_DTO)))
                .andExpect(status().isCreated());
    }
    @Test
    @SneakyThrows
    void updateProduct() throws Exception {
        mockMvc.perform(put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_DTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }
    @Test
    @SneakyThrows
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/products/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void productNotFoundForGet() throws Exception {
        mockMvc.perform(get("/api/v1/products/15"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void productNotFoundForPut() throws Exception {
        mockMvc.perform(put("/api/v1/products/15")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PRODUCT_DTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void productAlreadyExists() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_DTO)));
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_DTO)))
                .andExpect(status().isConflict());
    }

    @Test
    @SneakyThrows
    void cosmicWordName() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(INVALID_PRODUCT_DTO)))
                .andExpect(status().isBadRequest());
    }
}
