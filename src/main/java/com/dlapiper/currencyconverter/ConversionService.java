package com.dlapiper.currencyconverter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Currency;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConversionService {

    private Map<String, Double> conversionRates;
    String csvFilePath = "exchange-rates.csv";

    public ConversionService() {
        loadExchangeRatesFromCsv();
    }

    public ConversionService(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public double convertCurrency(String sourceCurrency, String targetCurrency, double amount) {
        validateCurrency(sourceCurrency);
        validateCurrency(targetCurrency);
        validateAmount(amount);

        if (sourceCurrency.equalsIgnoreCase(targetCurrency)) {
            return amount; // No conversion needed if source and target currencies are the same
        }

        double sourceAmountInGBP;
        sourceAmountInGBP = sourceCurrency.equalsIgnoreCase("GBP") ? amount : convertToGBP(sourceCurrency, amount);

        return targetCurrency.equalsIgnoreCase("GBP") ?
                sourceAmountInGBP :
                convertFromGBP(sourceAmountInGBP, targetCurrency);
    }

    private void loadExchangeRatesFromCsv() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(csvFilePath).getInputStream()))) {
            this.conversionRates = reader.lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toMap(
                            columns -> columns[2], // Currency code
                            columns -> Double.parseDouble(columns[3]), // Conversion rate to GBP
                            (existing, replacement) -> replacement //change this later. Needs a proper object because key is not unique.
                    ));
        } catch (IOException e) {
            throw new ExchangeRateLoadException("There was a problem loading the exchange rates CSV file.", e);
        }
    }

    void validateCurrency(String currencyCode) {
        if (Currency.getAvailableCurrencies().stream()
                .noneMatch(c -> c.getCurrencyCode().equalsIgnoreCase(currencyCode))) {
            throw new IllegalArgumentException("Invalid currency code: " + currencyCode);
        }
    }

    private void validateAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount: " + amount);
        }
    }

    private double convertToGBP(String currencyCode, double amount) {
        if (!conversionRates.containsKey(currencyCode)) {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + currencyCode);
        }
        double rateToGBP = conversionRates.get(currencyCode);
        return amount / rateToGBP;
    }

    private double convertFromGBP(double sourceAmountInGBP, String targetCurrency) {
        if (!conversionRates.containsKey(targetCurrency)) {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + targetCurrency);
        }
        double rateFromGBP = conversionRates.get(targetCurrency);
        return sourceAmountInGBP * rateFromGBP;
    }
}
