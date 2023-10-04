package com.dlapiper.currencyconverter.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Conversion(String source, String target, Double amount, Double result) {
    public Double getResult() {
        return BigDecimal.valueOf(result)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
