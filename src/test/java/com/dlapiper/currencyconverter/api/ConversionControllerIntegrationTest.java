package com.dlapiper.currencyconverter.api;

import com.dlapiper.currencyconverter.ConversionService;
import com.dlapiper.currencyconverter.model.ConversionModelAssembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ConversionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ConversionModelAssembler assembler;

    // Test cases

    @Test
    void testConvertEndpointWithValidInput() throws Exception {
        // Arrange
        var source = "USD";
        var target = "EUR";
        double amount = 100.0;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/convert")
                        .param("source", source)
                        .param("target", target)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.source").value(source))
                .andExpect(MockMvcResultMatchers.jsonPath("$.target").value(target))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(amount))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").isNotEmpty());
    }

    @Test
    void testConvertEndpointWithInvalidSourceCurrency() throws Exception {
        // Arrange
        var source = "POTATO";
        var target = "EUR";
        double amount = 100.0;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/convert")
                        .param("source", source)
                        .param("target", target)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testConvertEndpointWithInvalidTargetCurrency() throws Exception {
        // Arrange
        var source = "USD";
        var target = "POTATO";
        double amount = 100.0;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/convert")
                        .param("source", source)
                        .param("target", target)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testConvertEndpointWithNegativeAmount() throws Exception {
        // Arrange
        var source = "USD";
        var target = "EUR";
        double amount = -100.0;

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/convert")
                        .param("source", source)
                        .param("target", target)
                        .param("amount", String.valueOf(amount))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
