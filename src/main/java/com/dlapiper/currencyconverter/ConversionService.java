package com.dlapiper.currencyconverter;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Currency;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConversionService {

    private Map<String, Double> conversionRates;
    String csvFilePath = "classpath:exchange-rates.csv";

    public ConversionService() {
        loadExchangeRatesFromCsv();
    }

    public ConversionService(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    private void loadExchangeRatesFromCsv() {
        try {
            this.conversionRates = Files.lines(ResourceUtils.getFile(csvFilePath).toPath())
                    .map(line -> line.split(","))
                    .collect(Collectors.toMap(
                            columns -> columns[2], // Currency code
                            columns -> Double.parseDouble(columns[3]), // Conversion rate to GBP
                            (existing, replacement) -> replacement //change this later. Needs a proper object because key is not unique.
                    ));
        } catch (IOException e) {
            throw new ExchangeRateNotFoundException("There was a problem loading the exchange rates CSV file.");
        }
    }

    public double convertCurrency(String sourceCurrencyCode, String targetCurrencyCode, double amount) {
        // Implement your actual currency conversion logic here
        // This is just a placeholder logic, replace it with your real conversion logic
        if (sourceCurrencyCode.equals("USD") && targetCurrencyCode.equals("EUR")) {
            return amount * 0.85; // Placeholder conversion rate, actual rates will vary
        } else {
            throw new UnsupportedOperationException("Conversion not supported for provided currencies.");
        }
    }

    double convertToGBP(String currencyCode, double amount) {
        if (conversionRates == null) {
            throw new ExchangeRateNotFoundException("Conversion rates not loaded.");
        }
        // Perform currency conversion to GBP
        if (conversionRates.containsKey(currencyCode)) {
            double rateToGBP = conversionRates.get(currencyCode);
            return amount * rateToGBP;
        } else {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + currencyCode);
        }
    }

    boolean validateCurrency(String currencyCode) {
        return Currency.getAvailableCurrencies()
                .stream()
                .anyMatch(c -> c.getCurrencyCode().equalsIgnoreCase(currencyCode));
    }

}
