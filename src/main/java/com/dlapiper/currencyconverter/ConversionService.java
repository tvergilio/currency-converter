package com.dlapiper.currencyconverter;

import org.springframework.stereotype.Component;

@Component
public class ConversionService {
    public double convertCurrency(String sourceCurrencyCode, String targetCurrencyCode, double amount) {
        // Implement your actual currency conversion logic here
        // This is just a placeholder logic, replace it with your real conversion logic
        if (sourceCurrencyCode.equals("USD") && targetCurrencyCode.equals("EUR")) {
            return amount * 0.85; // Placeholder conversion rate, actual rates will vary
        } else {
            throw new UnsupportedOperationException("Conversion not supported for provided currencies.");
        }
    }


}
