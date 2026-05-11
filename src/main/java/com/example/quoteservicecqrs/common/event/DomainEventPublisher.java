package com.example.quoteservicecqrs.common.event;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
