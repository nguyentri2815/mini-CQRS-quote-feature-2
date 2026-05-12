package com.example.quoteservicecqrs.quote.intrastructure.search.service;

import com.example.quoteservicecqrs.common.exception.NotFoundException;
import com.example.quoteservicecqrs.quote.intrastructure.persistence.entity.QuoteEntity;
import com.example.quoteservicecqrs.quote.intrastructure.persistence.repository.QuoteJpaRepository;
import com.example.quoteservicecqrs.quote.intrastructure.search.document.QuoteDocument;
import com.example.quoteservicecqrs.quote.intrastructure.search.mapper.QuoteSearchMapper;
import com.example.quoteservicecqrs.quote.intrastructure.search.repository.QuoteSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuoteIndexService {

    private static final Logger log = LoggerFactory.getLogger(QuoteIndexService.class);

    private final QuoteJpaRepository quoteJpaRepository;
    private final QuoteSearchRepository quoteSearchRepository;
    private final QuoteSearchMapper quoteSearchMapper;

    public QuoteIndexService(
            QuoteJpaRepository quoteJpaRepository,
            QuoteSearchRepository quoteSearchRepository,
            QuoteSearchMapper quoteSearchMapper
    ) {
        this.quoteJpaRepository = quoteJpaRepository;
        this.quoteSearchRepository = quoteSearchRepository;
        this.quoteSearchMapper = quoteSearchMapper;
    }

    public void syncQuote(String quoteId) {
        log.info("[SYNC_ES] Start syncing quote to Elasticsearch. quoteId={}", quoteId);

        QuoteEntity entity = quoteJpaRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException("Quote not found: " + quoteId));

        QuoteDocument document = quoteSearchMapper.toDocument(entity);

        quoteSearchRepository.save(document);

        log.info("[SYNC_ES] Finished syncing quote to Elasticsearch. quoteId={}", quoteId);
    }
}

