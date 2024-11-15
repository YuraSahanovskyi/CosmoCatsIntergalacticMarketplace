package com.example.cosmocatsintergalacticmarketplace.service;

import com.example.cosmocatsintergalacticmarketplace.domain.CurrencyRate;

public interface CurrencyService {
    CurrencyRate getCurrencyRate(String currencyCode);
}
