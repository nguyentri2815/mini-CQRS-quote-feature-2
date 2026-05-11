package com.example.quoteservicecqrs.quote.intrastructure.search.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuoteIndexService {

    private static final Logger log = LoggerFactory.getLogger(QuoteIndexService.class);

    public void syncQuote(String quoteId) {
        log.info("[SYNC_ES] Syncing quote to Elasticsearch. quoteId={}", quoteId);
    }
}
