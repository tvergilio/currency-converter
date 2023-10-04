package com.dlapiper.currencyconverter.api;

import com.dlapiper.currencyconverter.ConversionService;
import com.dlapiper.currencyconverter.model.Conversion;
import com.dlapiper.currencyconverter.model.ConversionModelAssembler;
import org.springframework.hateoas.EntityModel;
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
    public EntityModel<Conversion> convert(String source,
                                           String target,
                                           Double amount) {
        var result = conversionService.convertCurrency(source, target, amount);
        return assembler.toModel(new Conversion(source, target, amount, result));
    }
}
