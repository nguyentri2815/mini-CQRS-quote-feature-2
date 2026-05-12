package com.example.quoteservicecqrs.quote.intrastructure.persistence.mapper;

import com.example.quoteservicecqrs.quote.domain.aggregate.QuoteAggregate;
import com.example.quoteservicecqrs.quote.intrastructure.persistence.entity.QuoteEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QuotePersistenceMapper {

    public QuoteEntity toNewEntity(QuoteAggregate aggregate) {
        LocalDateTime now = LocalDateTime.now();

        return new QuoteEntity(
                aggregate.getId(),
                aggregate.getQuoteNumber(),
                aggregate.getCustomerName(),
                aggregate.getProductCode(),
                aggregate.getPremium(),
                aggregate.getStatus(),
                now,
                now
        );
    }

    public QuoteAggregate toAggregate(QuoteEntity entity) {
        return QuoteAggregate.restore(
                entity.getId(),
                entity.getQuoteNumber(),
                entity.getCustomerName(),
                entity.getProductCode(),
                entity.getPremium(),
                entity.getStatus()
        );
    }

    public void updateEntity(QuoteEntity entity, QuoteAggregate aggregate) {
        entity.setStatus(aggregate.getStatus());
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
