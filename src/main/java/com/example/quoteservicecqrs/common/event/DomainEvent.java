package com.example.quoteservicecqrs.common.event;

import java.time.LocalDateTime;

public interface DomainEvent {

    String eventName();

    String aggregateId();

    LocalDateTime occurredAt();
}
