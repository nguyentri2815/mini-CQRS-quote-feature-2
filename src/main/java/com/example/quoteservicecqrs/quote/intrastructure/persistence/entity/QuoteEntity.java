package com.example.quoteservicecqrs.quote.intrastructure.persistence.entity;

import com.example.quoteservicecqrs.quote.domain.model.QuoteStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "quotes")
public class QuoteEntity {

    @Id
    private String id;

    @Column(name = "quote_number", nullable = false, unique = true)
    private String quoteNumber;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "premium", nullable = false)
    private BigDecimal premium;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private QuoteStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected QuoteEntity() {
    }

    public QuoteEntity(
            String id,
            String quoteNumber,
            String customerName,
            String productCode,
            BigDecimal premium,
            QuoteStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.quoteNumber = quoteNumber;
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

    public String getQuoteNumber() {
        return quoteNumber;
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

    public QuoteStatus getStatus() {
        return status;
    }

    public void setStatus(QuoteStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
