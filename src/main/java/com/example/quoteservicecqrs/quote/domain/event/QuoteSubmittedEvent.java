package com.example.quoteservicecqrs.quote.domain.event;

import com.example.quoteservicecqrs.common.event.DomainEvent;

import java.time.LocalDateTime;

public class QuoteSubmittedEvent implements DomainEvent {

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

    @Override
    public String eventName() {
        return "QuoteSubmittedEvent";
    }

    @Override
    public String aggregateId() {
        return quoteId;
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }

}
