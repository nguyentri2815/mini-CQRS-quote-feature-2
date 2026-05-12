package com.example.quoteservicecqrs.quote.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class QuoteListItemResponse {

    private final String id;
    private final String customerName;
    private final String productCode;
    private final BigDecimal premium;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public QuoteListItemResponse(
            String id,
            String customerName,
            String productCode,
            BigDecimal premium,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.customerName = customerName;
        this.productCode = productCode;
        this.premium = premium;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
