package com.example.cosmocatsintergalacticmarketplace.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CurrencyRateDto {
    private static final int CURRENCY_CODE_LENGTH = 3;
    @NotBlank(message = "Currency code is mandatory")
    @Size(min = CURRENCY_CODE_LENGTH, max = CURRENCY_CODE_LENGTH, message = "Currency code length must be 3 characters")
    String code;

    @NotNull(message = "Rate is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rate must be greater than zero")
    BigDecimal rate;
}
