package com.example.quoteservicecqrs.quote.intrastructure.persistence.adapter;

import com.example.quoteservicecqrs.common.exception.NotFoundException;
import com.example.quoteservicecqrs.quote.domain.aggregate.QuoteAggregate;
import com.example.quoteservicecqrs.quote.intrastructure.persistence.entity.QuoteEntity;
import com.example.quoteservicecqrs.quote.intrastructure.persistence.mapper.QuotePersistenceMapper;
import com.example.quoteservicecqrs.quote.intrastructure.persistence.repository.QuoteJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class QuotePersistenceAdapter {

    private final QuoteJpaRepository quoteJpaRepository;
    private final QuotePersistenceMapper quotePersistenceMapper;

    public QuotePersistenceAdapter(
            QuoteJpaRepository quoteJpaRepository,
            QuotePersistenceMapper quotePersistenceMapper
    ) {
        this.quoteJpaRepository = quoteJpaRepository;
        this.quotePersistenceMapper = quotePersistenceMapper;
    }

    public void saveNew(QuoteAggregate aggregate) {
        QuoteEntity entity = quotePersistenceMapper.toNewEntity(aggregate);
        quoteJpaRepository.save(entity);
    }

    public QuoteAggregate loadAggregate(String quoteId) {
        QuoteEntity entity = quoteJpaRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("Quote not found: " + quoteId));

        return quotePersistenceMapper.toAggregate(entity);
    }

    public void saveExisting(QuoteAggregate aggregate) {
        QuoteEntity entity = quoteJpaRepository.findById(aggregate.getId())
                .orElseThrow(() -> new NotFoundException("Quote not found: " + aggregate.getId()));

        quotePersistenceMapper.updateEntity(entity, aggregate);

        quoteJpaRepository.save(entity);
    }
}
