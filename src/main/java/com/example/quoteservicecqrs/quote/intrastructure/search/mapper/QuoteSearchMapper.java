package com.example.quoteservicecqrs.quote.intrastructure.search.mapper;

import com.example.quoteservicecqrs.quote.intrastructure.persistence.entity.QuoteEntity;
import com.example.quoteservicecqrs.quote.intrastructure.search.document.QuoteDocument;
import org.springframework.stereotype.Component;

@Component
public class QuoteSearchMapper {

    public QuoteDocument toDocument(QuoteEntity entity) {
        return new QuoteDocument(
                entity.getId(),
                entity.getCustomerName(),
                entity.getProductCode(),
                entity.getPremium(),
                entity.getStatus().name(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
