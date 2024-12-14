package com.example.cosmocatsintergalacticmarketplace.web.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.OrderAnalytic;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderAnalyticEntry;
import com.example.cosmocatsintergalacticmarketplace.dto.order.OrderAnalyticListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface WebOrderAnalyticMapper {
    @Mapping(target = "date", source = "date")
    @Mapping(target = "status", source = "status")
    OrderAnalyticEntry toOrderAnalyticEntry(OrderAnalytic orderAnalytic);

    List<OrderAnalyticEntry> toOrderAnalyticEntries(List<OrderAnalytic> orderAnalytics);

    default OrderAnalyticListDto toOrderAnalyticListDto(List<OrderAnalytic> orderAnalytics) {
        return OrderAnalyticListDto.builder().orderAnalyticEntryList(toOrderAnalyticEntries(orderAnalytics)).build();
    }
}
