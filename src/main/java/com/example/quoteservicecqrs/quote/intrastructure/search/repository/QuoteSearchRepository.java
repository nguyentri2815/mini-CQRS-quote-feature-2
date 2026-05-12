package com.example.quoteservicecqrs.quote.intrastructure.search.repository;

import com.example.quoteservicecqrs.quote.intrastructure.search.document.QuoteDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuoteSearchRepository
        extends ElasticsearchRepository<QuoteDocument, String> {
}
