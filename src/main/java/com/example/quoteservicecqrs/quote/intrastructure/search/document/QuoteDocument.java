package com.example.quoteservicecqrs.quote.intrastructure.search.document;

import org.springframework.data.elasticsearch.annotations.Document;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(indexName = "quote_index")
public class QuoteDocument {

    @Id
    private String id;

    private String customerName;

    private String productCode;

    private BigDecimal premium;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public QuoteDocument() {
    }

    public QuoteDocument(
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
