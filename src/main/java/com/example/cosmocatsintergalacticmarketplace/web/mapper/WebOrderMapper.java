package com.example.cosmocatsintergalacticmarketplace.web.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Order;
import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderDto;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderListDto;
import com.example.cosmocatsintergalacticmarketplace.service.ProductService;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public abstract class WebOrderMapper {
    @Autowired
    private ProductService productService;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "items", source = "items")
    public abstract OrderEntry toOrderEntry(Order order);

    @IterableMapping(elementTargetType = Long.class)
    Map<Long, Integer> mapProductsToProductIds(Map<Product, Integer> items) {
        return items.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getId(), Map.Entry::getValue));
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "items", source = "items")
    public abstract Order toOrder(OrderDto orderDto);

    @IterableMapping(elementTargetType = Product.class)
    Map<Product, Integer> mapProductIdsToProducts(Map<Long, Integer> items) {
        return items.entrySet().stream()
                .collect(Collectors.toMap(entry -> productService.getProductById(entry.getKey()), Map.Entry::getValue));
    }

    public OrderListDto toOrderListDto(List<Order> orderList) {
        return OrderListDto.builder().orderEntries(toOrderEntry(orderList)).build();
    }
    public abstract List<OrderEntry> toOrderEntry(List<Order> orders);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerName", source = "orderDto.customerName")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "items", source = "orderDto.items")
    public abstract Order toOrderWithId(OrderDto orderDto, Long id);

}
