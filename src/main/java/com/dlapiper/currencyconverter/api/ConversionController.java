package com.dlapiper.currencyconverter.api;

import com.dlapiper.currencyconverter.ConversionService;
import com.dlapiper.currencyconverter.model.Conversion;
import com.dlapiper.currencyconverter.model.ConversionModelAssembler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    private final ConversionService conversionService;
    private final ConversionModelAssembler assembler;

    ConversionController(ConversionService conversionService, ConversionModelAssembler assembler) {
        this.conversionService = conversionService;
        this.assembler = assembler;
    }

    @PostMapping("/api/convert")
    public EntityModel<Conversion> convert(@NotNull @NotEmpty String source,
                                           @NotNull @NotEmpty String target,
                                           @NotNull @NotEmpty Double amount) {
        var result = conversionService.convertCurrency(source, target, amount);
        return assembler.toModel(new Conversion(source, target, amount, result));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
