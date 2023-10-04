package com.dlapiper.currencyconverter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommandsTest {

    private Commands commands;

    @MockBean
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
        commands = new Commands(conversionService);
    }

    @Test
    void testConvert() {
        // Arrange
        var sourceCurrencyCode = "USD";
        var targetCurrencyCode = "EUR";
        var amount = 100.0;
        var convertedAmount = 94.0;

        // Mocking behavior of conversionService
        when(conversionService.convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount)).thenReturn(convertedAmount);

        // Act
        commands.convert(sourceCurrencyCode, targetCurrencyCode, amount);

        // Verify method calls
        verify(conversionService, times(1)).convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount);
        verifyNoMoreInteractions(conversionService);
    }

    @Test
    void testSetSourceWithValidCurrencyCode() {
        // Arrange
        var validCurrencyCode = "USD";

        // Act
        commands.setSourceCurrencyCode(validCurrencyCode);

        // Assert
        assertEquals(validCurrencyCode.toUpperCase(), commands.getSourceCurrencyCode());
    }

    @Test
    void testSetSourceWithLowerCaseCurrencyCode() {
        // Arrange
        var lowerCaseCurrencyCode = "usd";

        // Act
        commands.setSourceCurrencyCode(lowerCaseCurrencyCode);

        // Assert
        assertEquals(lowerCaseCurrencyCode.toUpperCase(), commands.getSourceCurrencyCode());
    }

    @Test
    void testSetSourceWithNullCurrencyCode() {
        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            commands.setSourceCurrencyCode(null);
        });

        assertEquals("Source currency code must not be null.", exception.getMessage());
    }

    @Test
    void testSetTargetWithValidCurrencyCode() {
        // Arrange
        var validCurrencyCode = "USD";

        // Act
        commands.setTargetCurrencyCode(validCurrencyCode);

        // Assert
        assertEquals(validCurrencyCode.toUpperCase(), commands.getTargetCurrencyCode());
    }

    @Test
    void testSetTargetWithLowerCaseCurrencyCode() {
        // Arrange
        var lowerCaseCurrencyCode = "usd";

        // Act
        commands.setTargetCurrencyCode(lowerCaseCurrencyCode);

        // Assert
        assertEquals(lowerCaseCurrencyCode.toUpperCase(), commands.getTargetCurrencyCode());
    }

    @Test
    void testSetTargetWithNullCurrencyCode() {
        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            commands.setTargetCurrencyCode(null);
        });

        assertEquals("Target currency code must not be null.", exception.getMessage());
    }

    @Test
    void testSetAmountWithValidAmount() {
        // Arrange
        var validAmount = 100.0;

        // Act
        commands.setAmount(validAmount);

        // Assert
        assertEquals(validAmount, commands.getAmount());
    }

    @Test
    void testSetAmountWithZeroAmount() {
        // Arrange
        var zeroAmount = 0.0;

        // Act
        commands.setAmount(zeroAmount);

        // Assert
        assertEquals(zeroAmount, commands.getAmount());
    }

    @Test
    void testRun() {
        // Arrange
        var sourceCurrencyCode = "USD";
        var targetCurrencyCode = "EUR";
        var amount = 100.0;
        var convertedAmount = 94.0;

        // Mocking behavior of conversionService
        when(conversionService.convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount)).thenReturn(convertedAmount);

        // Act
        commands.setSourceCurrencyCode(sourceCurrencyCode);
        commands.setTargetCurrencyCode(targetCurrencyCode);
        commands.setAmount(amount);
        commands.run();

        // Verify output and method calls
        verify(conversionService, times(1)).convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount);
        verifyNoMoreInteractions(conversionService);
    }
}