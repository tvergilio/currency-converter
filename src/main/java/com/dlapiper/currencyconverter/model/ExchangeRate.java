package com.dlapiper.currencyconverter.model;

public record ExchangeRate(String country, String currencyName, String currencyCode, Double rate) {
}
