package com.example.cosmocatsintergalacticmarketplace.service.impl;

import com.example.cosmocatsintergalacticmarketplace.domain.CurrencyRate;
import com.example.cosmocatsintergalacticmarketplace.dto.CurrencyRateDto;
import com.example.cosmocatsintergalacticmarketplace.service.CurrencyService;
import com.example.cosmocatsintergalacticmarketplace.service.mapper.CurrencyRateMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRateMapper currencyRateMapper;
    private final RestTemplate restTemplate;

    private final String externalApiUrl;


    public CurrencyServiceImpl(CurrencyRateMapper currencyRateMapper, RestTemplate restTemplate, @Value("${currency.api.url}") String externalApiUrl) {
        this.currencyRateMapper = currencyRateMapper;
        this.restTemplate = restTemplate;
        this.externalApiUrl = externalApiUrl;
    }


    @Override
    public CurrencyRate getCurrencyRate(String currencyCode) {
        CurrencyRateDto currencyRateDto = restTemplate.getForObject(externalApiUrl + "/rates?code=" + currencyCode, CurrencyRateDto.class);
        return currencyRateMapper.toCurrencyRate(currencyRateDto);
    }
}
