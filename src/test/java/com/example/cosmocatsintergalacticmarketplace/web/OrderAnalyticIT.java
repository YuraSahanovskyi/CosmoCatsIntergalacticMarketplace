package com.example.cosmocatsintergalacticmarketplace.web;

import com.example.cosmocatsintergalacticmarketplace.AbstractIT;
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
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Order analytic IT")
public class OrderAnalyticIT extends AbstractIT {

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
    void shouldGetAllOrderAnalytics() throws Exception {
        CategoryEntity categoryEntity = saveCategoryEntity();
        ProductEntity productEntity = saveProductEntity(categoryEntity);
        saveOrderEntity(productEntity);
        mockMvc.perform(get("/api/v1/admin/order-analytics/yura"))
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

    private void saveOrderEntity(ProductEntity productEntity) {
        OrderEntity orderEntity =  orderRepository.save(OrderEntity.builder()
                .customerName("yura")
                .date(LocalDateTime.now())
                .status("status")
                .build());
        orderEntity.getOrderProducts().add(OrderProductEntity.builder()
                .order(orderEntity)
                .product(productEntity)
                .quantity(1)
                .build());
        orderRepository.save(orderEntity);
    }
}
