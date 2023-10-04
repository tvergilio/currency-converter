package com.dlapiper.currencyconverter;

import java.io.IOException;

public class ExchangeRateLoadException extends RuntimeException {
    public ExchangeRateLoadException(String message, IOException cause) {
        super(message, cause);
    }
}