package com.example.quoteservicecqrs.quote.domain.event;

import java.time.LocalDateTime;

public class QuoteCreatedEvent {

    private final String quoteId;
    private final String createdBy;
    private final LocalDateTime occurredAt;

    public QuoteCreatedEvent(
            String quoteId,
            String createdBy,
            LocalDateTime occurredAt
    ) {
        this.quoteId = quoteId;
        this.createdBy = createdBy;
        this.occurredAt = occurredAt;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
