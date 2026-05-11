package com.example.quoteservicecqrs.quote.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class QuoteCreateRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    private String productCode;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal premium;

    public String getCustomerName() {
        return customerName;
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getPremium() {
        return premium;
    }
}
