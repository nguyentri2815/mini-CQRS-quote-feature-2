package com.example.quoteservicecqrs.quote.application.handler;

import com.example.quoteservice.common.exception.NotFoundException;
import com.example.quoteservicecqrs.quote.application.command.ApproveQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.CreateQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.SubmitQuoteCommand;
import com.example.quoteservicecqrs.quote.application.dto.QuoteResponse;
import com.example.quoteservicecqrs.quote.domain.aggregate.QuoteAggregate;
import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import com.example.quoteservicecqrs.quote.workflow.QuoteSyncWorkflow;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class QuoteCommandHandler {

    private final Map<String, QuoteAggregate> aggregateStore = new ConcurrentHashMap<>();

    private final QuoteSyncWorkflow quoteSyncWorkflow;
    public QuoteCommandHandler(QuoteSyncWorkflow quoteSyncWorkflow) {
        this.quoteSyncWorkflow = quoteSyncWorkflow;
    }

    public QuoteResponse handle(CreateQuoteCommand command) {
        QuoteAggregate aggregate = new QuoteAggregate();

        QuoteCreatedEvent event = aggregate.create(command);
        aggregate.apply(event);

        aggregateStore.put(aggregate.getId(), aggregate);

        // Ngày 1 chỉ return response.
        // Ngày sau sẽ publish event cho Workflow.
        quoteSyncWorkflow.onQuoteCreated(event);

        return new QuoteResponse(
                aggregate.getId(),
                aggregate.getStatus().name()
        );
    }

    public QuoteResponse handle(SubmitQuoteCommand command) {

        QuoteAggregate aggregate = findAggregateOrThrow(command.getQuoteId());

        QuoteSubmittedEvent event = aggregate.submit(command);
        aggregate.apply(event);
        quoteSyncWorkflow.onQuoteSubmitted(event);

        return new QuoteResponse(
                aggregate.getId(),
                aggregate.getStatus().name()
        );
    }

    public QuoteResponse handle(ApproveQuoteCommand command) {
        QuoteAggregate aggregate = findAggregateOrThrow(command.getQuoteId());

        QuoteApprovedEvent event = aggregate.approve(command);
        aggregate.apply(event);
        quoteSyncWorkflow.onQuoteApproved(event);

        return new QuoteResponse(
                aggregate.getId(),
                aggregate.getStatus().name()
        );
    }

    private QuoteAggregate findAggregateOrThrow(String quoteId) {
        QuoteAggregate aggregate = aggregateStore.get(quoteId);

        if (aggregate == null) {
            throw new NotFoundException("Quote not found: " + quoteId);
        }

        return aggregate;
    }

}
