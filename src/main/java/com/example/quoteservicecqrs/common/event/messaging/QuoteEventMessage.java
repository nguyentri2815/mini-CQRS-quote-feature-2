package com.example.quoteservicecqrs.common.event.messaging;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class QuoteEventMessage {

    private String eventName;

    private String quoteId;

    private String customerName;

    private String productCode;

    private BigDecimal premium;

    private String actor;

    private LocalDateTime occurredAt;

    public QuoteEventMessage() {
    }

    public QuoteEventMessage(
            String eventName,
            String quoteId,
            String actor,
            LocalDateTime occurredAt
    ) {
        this.eventName = eventName;
        this.quoteId = quoteId;
        this.actor = actor;
        this.occurredAt = occurredAt;
    }

    public QuoteEventMessage(
            String eventName,
            String quoteId,
            String customerName,
            String productCode,
            BigDecimal premium,
            String actor,
            LocalDateTime occurredAt
    ) {
        this.eventName = eventName;
        this.quoteId = quoteId;
        this.customerName = customerName;
        this.productCode = productCode;
        this.premium = premium;
        this.actor = actor;
        this.occurredAt = occurredAt;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(LocalDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }
}
