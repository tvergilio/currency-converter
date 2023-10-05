package com.dlapiper.currencyconverter;

import com.dlapiper.currencyconverter.model.Conversion;
import com.dlapiper.currencyconverter.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommandLineInterfaceTest {

    private CommandLineInterface commandLineInterface;

    @MockBean
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
        commandLineInterface = new CommandLineInterface(conversionService);
    }

    @Test
    void testConvert() {
        // Arrange
        var sourceCurrencyCode = "USD";
        var targetCurrencyCode = "EUR";
        var amount = 100.0;
        var convertedAmount = 94.0;
        var conversion = new Conversion(Country.USA, Country.EUROZONE, amount, convertedAmount);

        // Mocking behavior of conversionService
        when(conversionService.convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount)).thenReturn(conversion);

        // Act
        commandLineInterface.convert(sourceCurrencyCode, targetCurrencyCode, amount);

        // Verify method calls
        verify(conversionService, times(1)).convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount);
        verifyNoMoreInteractions(conversionService);
    }

    @Test
    void testSetSourceWithValidCurrencyCode() {
        // Arrange
        var validCurrencyCode = "USD";

        // Act
        commandLineInterface.setSourceCurrencyCode(validCurrencyCode);

        // Assert
        assertEquals(validCurrencyCode.toUpperCase(), commandLineInterface.getSourceCurrencyCode());
    }

    @Test
    void testSetSourceWithLowerCaseCurrencyCode() {
        // Arrange
        var lowerCaseCurrencyCode = "usd";

        // Act
        commandLineInterface.setSourceCurrencyCode(lowerCaseCurrencyCode);

        // Assert
        assertEquals(lowerCaseCurrencyCode.toUpperCase(), commandLineInterface.getSourceCurrencyCode());
    }

    @Test
    void testSetSourceWithNullCurrencyCode() {
        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class,
                () -> commandLineInterface.setSourceCurrencyCode(null));

        assertEquals("Source currency code must not be null.", exception.getMessage());
    }

    @Test
    void testSetTargetWithValidCurrencyCode() {
        // Arrange
        var validCurrencyCode = "USD";

        // Act
        commandLineInterface.setTargetCurrencyCode(validCurrencyCode);

        // Assert
        assertEquals(validCurrencyCode.toUpperCase(), commandLineInterface.getTargetCurrencyCode());
    }

    @Test
    void testSetTargetWithLowerCaseCurrencyCode() {
        // Arrange
        var lowerCaseCurrencyCode = "usd";

        // Act
        commandLineInterface.setTargetCurrencyCode(lowerCaseCurrencyCode);

        // Assert
        assertEquals(lowerCaseCurrencyCode.toUpperCase(), commandLineInterface.getTargetCurrencyCode());
    }

    @Test
    void testSetTargetWithNullCurrencyCode() {
        // Act and Assert
        var exception = assertThrows(IllegalArgumentException.class,
                () -> commandLineInterface.setTargetCurrencyCode(null));

        assertEquals("Target currency code must not be null.", exception.getMessage());
    }

    @Test
    void testSetAmountWithValidAmount() {
        // Arrange
        var validAmount = 100.0;

        // Act
        commandLineInterface.setAmount(validAmount);

        // Assert
        assertEquals(validAmount, commandLineInterface.getAmount());
    }

    @Test
    void testSetAmountWithZeroAmount() {
        // Arrange
        var zeroAmount = 0.0;

        // Act
        commandLineInterface.setAmount(zeroAmount);

        // Assert
        assertEquals(zeroAmount, commandLineInterface.getAmount());
    }

    @Test
    void testRun() {
        // Arrange
        var sourceCurrencyCode = "USD";
        var targetCurrencyCode = "EUR";
        var amount = 100.0;
        var convertedAmount = 94.0;
        var conversion = new Conversion(Country.USA, Country.EUROZONE, amount, convertedAmount);

        // Mocking behavior of conversionService
        when(conversionService.convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount)).thenReturn(conversion);

        // Act
        commandLineInterface.setSourceCurrencyCode(sourceCurrencyCode);
        commandLineInterface.setTargetCurrencyCode(targetCurrencyCode);
        commandLineInterface.setAmount(amount);
        commandLineInterface.run();

        // Verify output and method calls
        verify(conversionService, times(1)).convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount);
        verifyNoMoreInteractions(conversionService);
    }
}