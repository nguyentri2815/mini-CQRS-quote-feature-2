package com.example.quoteservicecqrs.common.event;

public interface DomainEventHandler<T extends DomainEvent> {

    Class<T> eventType();

    void handle(T event);
}
