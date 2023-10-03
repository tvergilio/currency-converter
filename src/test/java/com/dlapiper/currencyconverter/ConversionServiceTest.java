package com.dlapiper.currencyconverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    public void testUpperCaseCurrencyCode() {
        assertDoesNotThrow(() -> conversionService.validateCurrency("EUR"));
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
    public void testInvalidCurrencyCode() {
        assertThrows(IllegalArgumentException.class, () -> conversionService.validateCurrency("123"));
    }

    @Test
    void testConvertToGBPWithValidCurrencyCode() {
        // Act
        double convertedAmount = conversionService.convertToGBP("USD", 100);

        // Assert
        assertEquals(81.30, convertedAmount, 0.01);
    }

    @Test
    void testConvertToGBPWithInvalidCurrencyCode() {

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> conversionService.convertToGBP("POTATO", 100));
        assertEquals("Conversion rate not found for currency code: POTATO", exception.getMessage());
    }
    @Test
    void testConvertToGBPWithUnsupportedCurrencyCode() {

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> conversionService.convertToGBP("MYR", 100));
        assertEquals("Conversion rate not found for currency code: MYR", exception.getMessage());
    }

    @Test
    void testLoadConversionRatesFromCsvWithIOException() {
        // Arrange
        ConversionService spyConverter = spy(new ConversionService()); // Create a new spy instance of ConversionService

        try (MockedStatic<Files> filesMockedStatic = mockStatic(Files.class)) {
            filesMockedStatic.when(() -> Files.lines(any())).thenThrow(IOException.class);

            // Act and Assert
            assertThrows(ExchangeRateNotFoundException.class,
                    spyConverter::loadExchangeRatesFromCsv);
            // Assert the exception message or handle it as needed
        }
    }
}