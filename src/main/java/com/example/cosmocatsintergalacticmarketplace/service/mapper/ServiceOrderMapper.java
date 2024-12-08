package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.Order;
import com.example.cosmocatsintergalacticmarketplace.domain.Product;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderEntity;
import com.example.cosmocatsintergalacticmarketplace.repository.entity.OrderProductEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public abstract class ServiceOrderMapper {

    @Autowired
    private ServiceProductMapper serviceProductMapper;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "items", source = "orderProducts", qualifiedByName = "ProductOrderEntitiesToItems")  // This is correct for mapping to Order
    public abstract Order toOrder(OrderEntity orderEntity);

    @Named("ProductOrderEntitiesToItems")
    protected Map<Product, Integer> toItems(Set<OrderProductEntity> entitySet) {
        return entitySet.stream()
                .collect(Collectors.toMap(
                        orderProductEntity -> serviceProductMapper.toProduct(orderProductEntity.getProduct()),
                        OrderProductEntity::getQuantity));
    }

    public abstract List<Order> toOrders(List<OrderEntity> orderEntities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "orderProducts", source = "items", qualifiedByName = "ItemsToOrderEntityItems")
    public abstract OrderEntity toOrderEntity(Order order);

    @Named("ItemsToOrderEntityItems")
    protected Set<OrderProductEntity> mapItemsToOrderEntityItems(Map<Product, Integer> items) {
        return items.entrySet().stream()
                .map(entry -> OrderProductEntity.builder()
                        .product(serviceProductMapper.toProductEntity(entry.getKey()))
                        .quantity(entry.getValue())
                        .build())
                .collect(Collectors.toSet());
    }

    @AfterMapping
    protected void linkOrderProducts(@MappingTarget OrderEntity orderEntity) {
        if (orderEntity.getOrderProducts() != null) {
            orderEntity.getOrderProducts().forEach(orderProduct -> {
                orderProduct.setOrder(orderEntity);
            });
        }
    }
}
