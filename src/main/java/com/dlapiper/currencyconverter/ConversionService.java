package com.dlapiper.currencyconverter;

import com.dlapiper.currencyconverter.model.Conversion;
import com.dlapiper.currencyconverter.model.Country;
import com.dlapiper.currencyconverter.model.ExchangeRate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConversionService {

    private List<ExchangeRate> conversionRates;
    String csvFilePath = "exchange-rates.csv";

    public ConversionService() {
        loadExchangeRatesFromCsv();
    }

    public ConversionService(List<ExchangeRate> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public Conversion convertCurrency(String sourceCurrency, String targetCurrency, double amount) {
        validateCurrency(sourceCurrency);
        validateCurrency(targetCurrency);
        validateAmount(amount);

        if (sourceCurrency.equalsIgnoreCase(targetCurrency)) {
            return createConversionWithSameCurrencies(sourceCurrency, amount);
        }

        var sourceAmountInGBP = getSourceAmountInGBP(sourceCurrency, amount);
        var result = getResult(sourceAmountInGBP, targetCurrency);

        return createConversion(sourceCurrency, targetCurrency, amount, result);
    }

    private Conversion createConversionWithSameCurrencies(String currencyCode, double amount) {
        var country = Country.findCountryByCurrencyCode(currencyCode);
        return new Conversion(country, country, amount, amount);
    }

    private double getSourceAmountInGBP(String sourceCurrency, double amount) {
        return sourceCurrency.equalsIgnoreCase("GBP") ? amount : convertToGBP(sourceCurrency, amount);
    }

    private double getResult(double sourceAmountInGBP, String targetCurrency) {
        return targetCurrency.equalsIgnoreCase("GBP") ?
                sourceAmountInGBP :
                convertFromGBP(sourceAmountInGBP, targetCurrency);
    }

    private Conversion createConversion(String sourceCurrency, String targetCurrency, double amount, double result) {
        var sourceCountry = Country.findCountryByCurrencyCode(sourceCurrency);
        var targetCountry = Country.findCountryByCurrencyCode(targetCurrency);
        return new Conversion(sourceCountry, targetCountry, amount, result);
    }

    private void loadExchangeRatesFromCsv() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource(csvFilePath).getInputStream()))) {
            this.conversionRates = reader.lines()
                    .map(line -> {
                        String[] columns = line.split(",");
                        return new ExchangeRate(Country.findCountryByName(columns[0]), Double.parseDouble(columns[3]));
                    })
                    .collect(Collectors.toList());
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

    private ExchangeRate getExchangeRateByCurrencyCode(String currencyCode) {
        return conversionRates.stream()
                .filter(rate -> rate.country().getCurrencyCode().equalsIgnoreCase(currencyCode))
                .findFirst()
                .orElse(null);
    }

    private double convertToGBP(String currencyCode, double amount) {
        var exchangeRate = getExchangeRateByCurrencyCode(currencyCode);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + currencyCode);
        }
        var rateToGBP = exchangeRate.rate();
        return amount / rateToGBP;
    }

    private double convertFromGBP(double sourceAmountInGBP, String targetCurrency) {
        var exchangeRate = getExchangeRateByCurrencyCode(targetCurrency);
        if (exchangeRate == null) {
            throw new IllegalArgumentException("Conversion rate not found for currency code: " + targetCurrency);
        }
        var rateFromGBP = exchangeRate.rate();
        return sourceAmountInGBP * rateFromGBP;
    }
}
