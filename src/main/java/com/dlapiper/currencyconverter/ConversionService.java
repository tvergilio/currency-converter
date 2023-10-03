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

    void loadExchangeRatesFromCsv() {
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

    public double convertCurrency(String sourceCurrency, String targetCurrency, double amount) {
        validateCurrency(sourceCurrency);
        validateCurrency(targetCurrency);

        if (sourceCurrency.equalsIgnoreCase(targetCurrency)) {
            return amount; // No conversion needed if source and target currencies are the same
        }

        double sourceAmountInGBP;
        sourceAmountInGBP = sourceCurrency.equalsIgnoreCase("GBP") ? amount : convertToGBP(sourceCurrency, amount);

        return targetCurrency.equalsIgnoreCase("GBP") ?
                sourceAmountInGBP :
                convertFromGBP(sourceAmountInGBP, targetCurrency);
    }

    void validateCurrency(String currencyCode) {
        if (!Currency.getAvailableCurrencies().stream()
                .anyMatch(c -> c.getCurrencyCode().equalsIgnoreCase(currencyCode))) {
            throw new IllegalArgumentException("Invalid currency code: " + currencyCode);
        }
    }

    double convertToGBP(String currencyCode, double amount) {
        if (!conversionRates.containsKey(currencyCode)) {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + currencyCode);
        }
        double rateToGBP = conversionRates.get(currencyCode);
        return amount / rateToGBP;
    }

    double convertFromGBP(double sourceAmountInGBP, String targetCurrency) {
        if (!conversionRates.containsKey(targetCurrency)) {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + targetCurrency);
        }
        double rateFromGBP = conversionRates.get(targetCurrency);
        return sourceAmountInGBP * rateFromGBP;
    }
}
