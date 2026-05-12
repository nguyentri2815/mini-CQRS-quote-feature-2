package com.example.quoteservicecqrs.common.event.messaging;

import com.example.quoteservicecqrs.common.event.DomainEvent;
import com.example.quoteservicecqrs.common.event.DomainEventPublisher;
import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class RabbitMqQuoteEventPublisher implements DomainEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqQuoteEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqQuoteEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(DomainEvent event) {
        QuoteEventMessage message = toMessage(event);

        log.info(
                "[RABBIT_PUBLISHER] Publishing quote event. eventName={}, quoteId={}",
                message.getEventName(),
                message.getQuoteId()
        );

        rabbitTemplate.convertAndSend(
                QuoteEventRabbitConfig.QUOTE_EVENT_EXCHANGE,
                QuoteEventRabbitConfig.QUOTE_EVENT_ROUTING_KEY,
                message
        );
    }

    private QuoteEventMessage toMessage(DomainEvent event) {
        if (event instanceof QuoteCreatedEvent quoteCreatedEvent) {
            return new QuoteEventMessage(
                    event.eventName(),
                    quoteCreatedEvent.getQuoteId(),
                    quoteCreatedEvent.getCustomerName(),
                    quoteCreatedEvent.getProductCode(),
                    quoteCreatedEvent.getPremium(),
                    quoteCreatedEvent.getCreatedBy(),
                    event.occurredAt()
            );
        }

        if (event instanceof QuoteSubmittedEvent quoteSubmittedEvent) {
            return new QuoteEventMessage(
                    event.eventName(),
                    quoteSubmittedEvent.getQuoteId(),
                    quoteSubmittedEvent.getSubmittedBy(),
                    event.occurredAt()
            );
        }

        if (event instanceof QuoteApprovedEvent quoteApprovedEvent) {
            return new QuoteEventMessage(
                    event.eventName(),
                    quoteApprovedEvent.getQuoteId(),
                    quoteApprovedEvent.getApprovedBy(),
                    event.occurredAt()
            );
        }

        throw new IllegalArgumentException("Unsupported event type: " + event.eventName());
    }
}
