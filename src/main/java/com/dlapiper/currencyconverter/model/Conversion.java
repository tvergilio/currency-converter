package com.dlapiper.currencyconverter.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Conversion(Country source, Country target, Double amount, Double result) {

    public static final String RESULT_FORMAT = "%.2f %s %ss";
    public Double getResult() {
        return BigDecimal.valueOf(result)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
