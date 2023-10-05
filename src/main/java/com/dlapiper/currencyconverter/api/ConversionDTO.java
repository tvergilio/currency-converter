package com.dlapiper.currencyconverter.api;

public record ConversionDTO(String source, String target, Double amount, Double result, String formatted) {
}
