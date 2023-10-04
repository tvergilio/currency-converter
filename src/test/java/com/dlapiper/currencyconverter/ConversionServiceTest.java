package com.dlapiper.currencyconverter;

import com.dlapiper.currencyconverter.model.ExchangeRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConversionServiceTest {

    @InjectMocks
    private ConversionService conversionService;

    @Mock
    private List<ExchangeRate> mockConversionRates;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Creating mock conversion rates as a List of ExchangeRate objects
        mockConversionRates = Arrays.asList(
        new ExchangeRate("USA", "Dollar", "USD", 1.23),
        new ExchangeRate("Eurozone", "Euro", "EUR", 1.16),
        new ExchangeRate("Abu Dhabi", "Dirham", "AED", 4.54));

        // Creating the ConversionService instance with mock conversion rates
        conversionService = new ConversionService(mockConversionRates);
    }

    @Test
    void testConvertCurrencyWithValidConversion() {
        // Arrange
        var sourceCurrency = "USD";
        var targetCurrency = "EUR";
        var amount = 100;

        // Act
        var convertedAmount = conversionService.convertCurrency(sourceCurrency, targetCurrency, amount);

        // Assert
        assertEquals(94.30, convertedAmount, 0.01);
    }

    @Test
    void testConvertCurrencyWithInvalidTargetCurrency() {
        // Arrange
        var sourceAmountInGBP = 100;
        var sourceCurrency = "USD";
        var targetCurrency = "POTATO";

        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            conversionService.convertCurrency(sourceCurrency, targetCurrency, sourceAmountInGBP);
        });

        assertEquals("Invalid currency code: POTATO", exception.getMessage());
    }

    @Test
    void testConvertCurrencyWithInvalidAmount() {
        // Arrange
        var sourceAmountInGBP = -100;
        var sourceCurrency = "GBP";
        var targetCurrency = "USD";

        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class,
                () -> conversionService.convertCurrency(sourceCurrency, targetCurrency, sourceAmountInGBP));

        assertEquals("Invalid amount: -100.0", exception.getMessage());
    }

    @Test
    void testConvertToGBPWithValidCurrencyCode() {
        // Arrange
        var sourceCurrency = "USD";
        var targetCurrency = "GBP";
        var sourceAmountInUSD = 100;

        // Act
        var convertedAmount = conversionService.convertCurrency(sourceCurrency, targetCurrency, sourceAmountInUSD);

        // Assert
        assertEquals(81.30, convertedAmount, 0.01);
    }

    @Test
    void testConvertToGBPWithInvalidCurrencyCode() {
        //Arrange
        var sourceCurrency = "POTATO";
        var targetCurrency = "GBP";
        var sourceAmount = 100;

        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class,
                () -> conversionService.convertCurrency(sourceCurrency, targetCurrency, sourceAmount));
        assertEquals("Invalid currency code: POTATO", exception.getMessage());
    }

    @Test
    void testConvertToGBPWithUnsupportedCurrencyCode() {
        //Arrange
        var sourceCurrency = "MYR";
        var targetCurrency = "GBP";
        var sourceAmountInMYR = 100;

        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class,
                () -> conversionService.convertCurrency(sourceCurrency, targetCurrency, sourceAmountInMYR));
        assertEquals("Conversion rate not found for currency code: MYR", exception.getMessage());
    }


    @Test
    void testConvertCurrencyWithSameCurrencies() {
        // Arrange
        var sourceCurrency = "USD";
        var targetCurrency = "USD";
        var amount = 100;

        // Act
        var convertedAmount = conversionService.convertCurrency(sourceCurrency, targetCurrency, amount);

        // Assert
        assertEquals(100, convertedAmount);
    }
    @Test
    public void testValidateValidCurrency() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("USD"));
    }

    @Test
    public void testValidateInvalidCurrency() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency("POTATO"));
    }

    @Test
    public void testValidateMixedCaseCurrencyCode() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("EuR"));
    }

    @Test
    public void testValidateLowerCaseCurrencyCode() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("usd"));
    }

    @Test
    public void testValidateNullCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency(null));
    }

    @Test
    public void testValidateEmptyCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency(""));
    }

    @Test
    public void testValidateNumericCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency("123"));
    }
}