package com.example.quoteservicecqrs.common.event.messaging;

import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import com.example.quoteservicecqrs.quote.workflow.QuoteSyncWorkflow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QuoteEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(QuoteEventConsumer.class);

    private final QuoteSyncWorkflow quoteSyncWorkflow;

    public QuoteEventConsumer(QuoteSyncWorkflow quoteSyncWorkflow) {
        this.quoteSyncWorkflow = quoteSyncWorkflow;
    }

    @RabbitListener(queues = QuoteEventRabbitConfig.QUOTE_WORKFLOW_QUEUE)
    public void consume(QuoteEventMessage message) {
        log.info(
                "[RABBIT_CONSUMER] Consuming quote event. eventName={}, quoteId={}",
                message.getEventName(),
                message.getQuoteId()
        );

        switch (message.getEventName()) {
            case "QuoteCreatedEvent" -> handleQuoteCreated(message);
            case "QuoteSubmittedEvent" -> handleQuoteSubmitted(message);
            case "QuoteApprovedEvent" -> handleQuoteApproved(message);
            default -> throw new IllegalArgumentException(
                    "Unsupported quote event: " + message.getEventName()
            );
        }
    }

    private void handleQuoteCreated(QuoteEventMessage message) {
        QuoteCreatedEvent event = new QuoteCreatedEvent(
                message.getQuoteId(),
                message.getCustomerName(),
                message.getProductCode(),
                message.getPremium(),
                message.getActor(),
                message.getOccurredAt()
        );

        quoteSyncWorkflow.onQuoteCreated(event);
    }

    private void handleQuoteSubmitted(QuoteEventMessage message) {
        QuoteSubmittedEvent event = new QuoteSubmittedEvent(
                message.getQuoteId(),
                message.getActor(),
                message.getOccurredAt()
        );

        quoteSyncWorkflow.onQuoteSubmitted(event);
    }

    private void handleQuoteApproved(QuoteEventMessage message) {
        QuoteApprovedEvent event = new QuoteApprovedEvent(
                message.getQuoteId(),
                message.getActor(),
                message.getOccurredAt()
        );

        quoteSyncWorkflow.onQuoteApproved(event);
    }
}
