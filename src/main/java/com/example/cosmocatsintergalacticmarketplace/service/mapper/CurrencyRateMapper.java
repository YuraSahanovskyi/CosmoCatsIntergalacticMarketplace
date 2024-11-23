package com.example.cosmocatsintergalacticmarketplace.service.mapper;

import com.example.cosmocatsintergalacticmarketplace.domain.CurrencyRate;
import com.example.cosmocatsintergalacticmarketplace.dto.CurrencyRateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyRateMapper {
    @Mapping(target = "code", source = "code")
    @Mapping(target = "rate", source = "rate")
    CurrencyRate toCurrencyRate(CurrencyRateDto currencyRateDto);
}
