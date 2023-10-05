package com.dlapiper.currencyconverter.api;

import com.dlapiper.currencyconverter.api.ConversionDTO;
import com.dlapiper.currencyconverter.model.Conversion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ConversionModelAssembler implements RepresentationModelAssembler<Conversion, EntityModel<ConversionDTO>> {

    @Override
    public EntityModel<ConversionDTO> toModel(Conversion conversion) {
        return EntityModel.of(createConversionDTO(conversion));
    }

    private ConversionDTO createConversionDTO(Conversion conversion) {
        String resultFormatted = String.format("%.2f %s %s",
                conversion.getResult(), conversion.target().getName(), conversion.target().getCurrencyName());

        return new ConversionDTO(
                conversion.source().getCurrencyCode(),
                conversion.target().getCurrencyCode(),
                conversion.amount(),
                conversion.getResult(),
                resultFormatted
        );
    }

}


