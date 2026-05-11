package com.example.quoteservicecqrs.quote.domain.event;

import java.time.LocalDateTime;

public class QuoteSubmittedEvent {

    private final String quoteId;
    private final String submittedBy;
    private final LocalDateTime occurredAt;

    public QuoteSubmittedEvent(
            String quoteId,
            String submittedBy,
            LocalDateTime occurredAt
    ) {
        this.quoteId = quoteId;
        this.submittedBy = submittedBy;
        this.occurredAt = occurredAt;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
