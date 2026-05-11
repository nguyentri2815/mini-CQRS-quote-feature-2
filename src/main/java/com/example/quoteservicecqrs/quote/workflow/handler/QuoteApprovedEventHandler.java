package com.example.quoteservicecqrs.quote.workflow.handler;

import com.example.quoteservicecqrs.common.event.DomainEventHandler;
import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.workflow.QuoteSyncWorkflow;
import org.springframework.stereotype.Component;

@Component
public class QuoteApprovedEventHandler implements DomainEventHandler<QuoteApprovedEvent> {

    private final QuoteSyncWorkflow quoteSyncWorkflow;

    public QuoteApprovedEventHandler(QuoteSyncWorkflow quoteSyncWorkflow) {
        this.quoteSyncWorkflow = quoteSyncWorkflow;
    }

    @Override
    public Class<QuoteApprovedEvent> eventType() {
        return QuoteApprovedEvent.class;
    }

    @Override
    public void handle(QuoteApprovedEvent event) {
        quoteSyncWorkflow.onQuoteApproved(event);
    }
}
