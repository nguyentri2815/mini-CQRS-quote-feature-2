package com.example.quoteservicecqrs.quote.intrastructure.allocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuoteAllocationGateway {

    private static final Logger log = LoggerFactory.getLogger(QuoteAllocationGateway.class);

    public void sendSubmitQuoteAllocation(String quoteId) {
        log.info("[ALLOCATION] Send submit quote allocation message. quoteId={}", quoteId);
    }

    public void sendApproveQuoteAllocation(String quoteId) {
        log.info("[ALLOCATION] Send approve quote allocation message. quoteId={}", quoteId);
    }
}
