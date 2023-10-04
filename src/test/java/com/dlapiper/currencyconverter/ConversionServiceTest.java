package com.dlapiper.currencyconverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConversionServiceTest {

    @InjectMocks
    private ConversionService conversionService;

    @Mock
    private Map<String, Double> mockConversionRates;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Creating mock conversion rates
        mockConversionRates = new HashMap<>();
        mockConversionRates.put("USD", 1.23);
        mockConversionRates.put("EUR", 1.16);
        mockConversionRates.put("AED", 4.54);

        // Creating the ConversionService instance with mock conversion rates
        conversionService = new ConversionService(mockConversionRates);
    }

    @Test
    public void testValidCurrency() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("USD"));
    }

    @Test
    public void testInvalidCurrency() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency("INVALID"));
    }

    @Test
    public void testMixedCaseCurrencyCode() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("EuR"));
    }

    @Test
    public void testLowerCaseCurrencyCode() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("usd"));
    }

    @Test
    public void testNullCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency(null));
    }

    @Test
    public void testEmptyCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency(""));
    }

    @Test
    public void testNumericCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency("123"));
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
}