package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Order;
import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.dto.OrderEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.ProductEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "items", source = "order", expression = "java(convertItems(order.getItems()))")
    OrderEntry toOrderEntry(Order order);

    default Map<ProductEntry, Integer> convertItems(Map<Product, Integer> items) {
        Map<ProductEntry, Integer> convertedItems = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            convertedItems.put(Mappers.getMapper(ProductMapper.class).toProductEntry(entry.getKey()), entry.getValue());
        }
        return convertedItems;
    }
}
