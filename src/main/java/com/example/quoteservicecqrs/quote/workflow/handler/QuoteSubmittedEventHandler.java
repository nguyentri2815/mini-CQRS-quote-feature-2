package com.example.quoteservicecqrs.quote.workflow.handler;

import com.example.quoteservicecqrs.common.event.DomainEventHandler;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import com.example.quoteservicecqrs.quote.workflow.QuoteSyncWorkflow;
import org.springframework.stereotype.Component;

@Component
public class QuoteSubmittedEventHandler implements DomainEventHandler<QuoteSubmittedEvent> {

    private final QuoteSyncWorkflow quoteSyncWorkflow;

    public QuoteSubmittedEventHandler(QuoteSyncWorkflow quoteSyncWorkflow) {
        this.quoteSyncWorkflow = quoteSyncWorkflow;
    }

    @Override
    public Class<QuoteSubmittedEvent> eventType() {
        return QuoteSubmittedEvent.class;
    }

    @Override
    public void handle(QuoteSubmittedEvent event) {
        quoteSyncWorkflow.onQuoteSubmitted(event);
    }
}
