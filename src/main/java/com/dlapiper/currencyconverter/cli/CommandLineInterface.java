package com.dlapiper.currencyconverter.cli;

import com.dlapiper.currencyconverter.ConversionService;
import com.dlapiper.currencyconverter.model.Conversion;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CommandLineInterface {
    private String sourceCurrencyCode;
    private String targetCurrencyCode;
    private Double amount;

    private final ConversionService conversionService;

    public CommandLineInterface(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    String getSourceCurrencyCode() {
        return sourceCurrencyCode;
    }

    String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }

    Double getAmount() {
        return amount;
    }

    @ShellMethod(key = "set source", value = "Set source ISO 4217 currency code")
    public void setSourceCurrencyCode(String sourceCurrencyCode) {
        if (sourceCurrencyCode == null) {
            throw new IllegalArgumentException("Source currency code must not be null.");
        }
        this.sourceCurrencyCode = sourceCurrencyCode.toUpperCase();
    }

    @ShellMethod(key = "set target", value = "Set target ISO 4217 currency code")
    public void setTargetCurrencyCode(String targetCurrencyCode) {
        if (targetCurrencyCode == null) {
            throw new IllegalArgumentException("Target currency code must not be null.");
        }
        this.targetCurrencyCode = targetCurrencyCode.toUpperCase();
    }

    @ShellMethod(key = "set amount", value = "Set source currency amount")
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @ShellMethod(key = "run", value = "Runs currency conversion based on preset values")
    public String run() {
        var conversion = conversionService.convertCurrency(sourceCurrencyCode, targetCurrencyCode, amount);
        var target = conversion.target();
        resetInputs();
        return String.format((Conversion.RESULT_FORMAT) + "%n", conversion.result(), target.getName(), target.getCurrencyName());
    }

    @ShellMethod(key = "convert", value = "Performs conversion with a one-line command")
    public String convert(@ShellOption(help = "Source ISO 4217 currency code") String source,
                        @ShellOption(help = "Target ISO 4217 currency code") String target,
                        @ShellOption(help = "Source currency amount in format x.yy") double amount) {

        if (source != null) {
            setSourceCurrencyCode(source.toUpperCase());
        }
        if (target != null) {
            setTargetCurrencyCode(target.toUpperCase());
        }
        if (amount > 0) {
            setAmount(amount);
        }
        return run();
    }

    private void resetInputs() {
        sourceCurrencyCode = null;
        targetCurrencyCode = null;
        amount = null;
    }
}
