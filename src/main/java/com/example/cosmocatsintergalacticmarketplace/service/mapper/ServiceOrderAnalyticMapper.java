package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.OrderAnalytic;
import com.example.cosmocatsintergalacticmarketplace.repository.projection.OrderProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ServiceOrderAnalyticMapper {
    @Mapping(target = "date", source = "date")
    @Mapping(target = "status", source = "status")
    OrderAnalytic toOrderAnalytic(OrderProjection orderProjection);

    List<OrderAnalytic> toOrderAnalyticList(List<OrderProjection> orderProjections);
}
