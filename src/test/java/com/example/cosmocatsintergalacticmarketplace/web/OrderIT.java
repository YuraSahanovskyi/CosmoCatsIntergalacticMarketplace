package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.AbstractIT;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderDto;
import com.example.cosmocatsintergalacticmarketplace.repository.CategoryRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.OrderRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.ProductRepository;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.CategoryEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderProductEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.ProductEntity;
import com.example.cosmocatsintergalacticmarketplace.service.CategoryService;
import com.example.cosmocatsintergalacticmarketplace.service.OrderService;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Order Controller IT")
public class OrderIT extends AbstractIT {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @SpyBean
    private OrderService orderService;

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
        reset(orderService);
        orderRepository.deleteAll();
        reset(productService);
        productRepository.deleteAll();
        reset(categoryService);
        categoryRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void shouldGetAllOrders() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        ProductEntity productEntity = saveProductEntity(categoryEntity);
        saveOrderEntity(productEntity);
        mockMvc.perform(get("/api/v1/orders"))
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

    private OrderEntity saveOrderEntity(ProductEntity productEntity) {
        OrderEntity orderEntity =  orderRepository.save(OrderEntity.builder()
                .customerName("Customer")
                .date(LocalDateTime.now())
                .status("status")
                .build());
        orderEntity.getOrderProducts().add(OrderProductEntity.builder()
                .order(orderEntity)
                .product(productEntity)
                .quantity(1)
                .build());
        return orderRepository.save(orderEntity);
    }

    @Test
    @SneakyThrows
    void shouldGetOrderById() throws Exception {
        ProductEntity productEntity = saveProductEntity(saveCategoryEntity());
        OrderEntity orderEntity = saveOrderEntity(productEntity);
        mockMvc.perform(get("/api/v1/orders/" + orderEntity.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldSaveOrder() throws Exception {
        ProductEntity productEntity = saveProductEntity(saveCategoryEntity());
        OrderDto orderDto = getOrderDto(productEntity.getId());
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated());
    }

    private OrderDto getOrderDto(Long productId) {
        Map<Long, Integer> items = new HashMap<>();
        items.put(productId, 1);
        return OrderDto.builder()
                .customerName("Customer 1")
                .items(items)
                .build();
    }

    @Test
    @SneakyThrows
    void shouldUpdateOrder() throws Exception {
        ProductEntity productEntity = saveProductEntity(saveCategoryEntity());
        OrderEntity orderEntity = saveOrderEntity(productEntity);
        OrderDto orderDto = getUpdatedOrderDto(productEntity.getId());
        mockMvc.perform(put("/api/v1/orders/" + orderEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated());
    }

    private OrderDto getUpdatedOrderDto(Long productId) {
        Map<Long, Integer> items = new HashMap<>();
        items.put(productId, 3);
        return OrderDto.builder()
                .customerName("New Customer 1")
                .status("ready")
                .items(items)
                .build();
    }

    @Test
    @SneakyThrows
    void shouldDeleteOrder() throws Exception {
        OrderEntity orderEntity = saveOrderEntity(
                saveProductEntity(
                        saveCategoryEntity()));
        mockMvc.perform(delete("/api/v1/orders/" + orderEntity.getId()))
                .andExpect(status().isNoContent());
        assertFalse(orderRepository.existsById(orderEntity.getId()));
    }

    @Test
    @SneakyThrows
    void shouldGetNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isNotFound());
    }
}
