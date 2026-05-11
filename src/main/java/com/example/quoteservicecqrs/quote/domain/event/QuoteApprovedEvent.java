package com.example.quoteservicecqrs.quote.domain.event;

import java.time.LocalDateTime;

public class QuoteApprovedEvent {

    private final String quoteId;
    private final String approvedBy;
    private final LocalDateTime occurredAt;

    public QuoteApprovedEvent(
            String quoteId,
            String approvedBy,
            LocalDateTime occurredAt
    ) {
        this.quoteId = quoteId;
        this.approvedBy = approvedBy;
        this.occurredAt = occurredAt;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
