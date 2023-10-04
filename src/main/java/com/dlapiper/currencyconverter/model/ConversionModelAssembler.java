package com.dlapiper.currencyconverter.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ConversionModelAssembler implements RepresentationModelAssembler<Conversion, EntityModel<Conversion>> {

    @Override
    public EntityModel<Conversion> toModel(Conversion conversion) {
        return EntityModel.of(conversion);
    }
}