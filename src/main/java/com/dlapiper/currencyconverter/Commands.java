package com.dlapiper.currencyconverter;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class Commands {

    private String sourceCurrencyCode;
    private String targetCurrencyCode;
    private Double amount;

    private final ConversionService conversionService;

    public Commands(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @ShellMethod(key = "set source", value = "Set source ISO 4217 currency code")
    public void setSource(String sourceCurrencyCode) {
        if (sourceCurrencyCode == null) {
            throw new IllegalArgumentException("Source currency code must not be null.");
        }
        this.sourceCurrencyCode = sourceCurrencyCode.toUpperCase();
    }

    @ShellMethod(key = "set target", value = "Set target ISO 4217 currency code")
    public void setTarget(String targetCurrencyCode) {
        if (targetCurrencyCode == null) {
            throw new IllegalArgumentException("Target currency code must not be null.");
        }
        this.targetCurrencyCode = targetCurrencyCode.toUpperCase();
    }

    @ShellMethod(key = "set amount", value = "Set source currency amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @ShellMethod(key = "run", value = "Runs currency conversion currency")
    public void run() {
        double convertedAmount = conversionService.convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount);
        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrencyCode);
        resetInputs();
    }

    @ShellMethod(key = "convert", value = "Converts currency")
    public void convert(@ShellOption(help = "Source ISO 4217 currency code") String source,
                        @ShellOption(help = "Target ISO 4217 currency code") String target,
                        @ShellOption(help = "Source currency amount in format x.yy") double amount) {

        if (source != null) {
            setSource(source.toUpperCase());
        }
        if (target != null) {
            setTarget(target.toUpperCase());
        }
        if (amount > 0) {
            setAmount(amount);
        }
        run();
    }

    private void resetInputs() {
        sourceCurrencyCode = null;
        targetCurrencyCode = null;
        amount = null;
    }

}
