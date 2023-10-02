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

        // Stubbing the behavior of conversionService.getConversionRates()
        // when the method is called
        conversionService = new ConversionService(mockConversionRates);
    }

    @Test
    public void testValidCurrency() {
        assertTrue(conversionService.validateCurrency("USD"));
    }

    @Test
    public void testInvalidCurrency() {
        assertFalse(conversionService.validateCurrency("INVALID"));
    }

    @Test
    public void testUpperCaseCurrencyCode() {
        assertTrue(conversionService.validateCurrency("eur"));
    }

    @Test
    public void testLowerCaseCurrencyCode() {
        assertTrue(conversionService.validateCurrency("usd"));
    }

    @Test
    public void testNullCurrencyCode() {
        assertFalse(conversionService.validateCurrency(null));
    }

    @Test
    public void testEmptyCurrencyCode() {
        assertFalse(conversionService.validateCurrency(""));
    }

    @Test
    void testConvertToGBPWithValidCurrencyCode() throws IOException {
        // Act
        double convertedAmount = conversionService.convertToGBP("USD", 100);

        // Assert
        assertEquals(123.0, convertedAmount);
    }

    @Test
    void testConvertToGBPWithInvalidCurrencyCode() throws IOException {

        // Act and Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> conversionService.convertToGBP("USX", 100));
        assertEquals("Conversion rate not found for currency code: USX", exception.getMessage());
    }

    @Test
    void testConvertToGBPWithIOException() {
        // Arrange
        ConversionService mockConverter = mock(ConversionService.class);
        when(mockConverter.convertToGBP("USD", 100)).thenCallRealMethod(); // Call the real method

        try (MockedStatic<Files> filesMockedStatic = mockStatic(Files.class)) {
            filesMockedStatic.when(() -> Files.lines(any())).thenThrow(IOException.class);

            // Act and Assert
            var exception = assertThrows(ExchangeRateNotFoundException.class,
                    () -> mockConverter.convertToGBP("USD", 100));
            // Assert the exception message or handle it as needed
        }
    }
}