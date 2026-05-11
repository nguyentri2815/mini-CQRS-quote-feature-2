package com.example.quoteservicecqrs.quote.workflow.handler;

import com.example.quoteservicecqrs.common.event.DomainEventHandler;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.workflow.QuoteSyncWorkflow;
import org.springframework.stereotype.Component;

@Component
public class QuoteCreatedEventHandler implements DomainEventHandler<QuoteCreatedEvent> {

    private final QuoteSyncWorkflow quoteSyncWorkflow;

    public QuoteCreatedEventHandler(QuoteSyncWorkflow quoteSyncWorkflow) {
        this.quoteSyncWorkflow = quoteSyncWorkflow;
    }

    @Override
    public Class<QuoteCreatedEvent> eventType() {
        return QuoteCreatedEvent.class;
    }

    @Override
    public void handle(QuoteCreatedEvent event) {
        quoteSyncWorkflow.onQuoteCreated(event);
    }
}
