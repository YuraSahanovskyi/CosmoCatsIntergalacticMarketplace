package com.example.cosmocatsintergalacticmarketplace.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CurrencyRate {
    String code;
    BigDecimal rate;
}
