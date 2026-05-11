package com.example.quoteservicecqrs.quote.intrastructure.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuoteNotificationService {

    private static final Logger log = LoggerFactory.getLogger(QuoteNotificationService.class);

    public void notifyQuoteCreated(String quoteId) {
        log.info("[NOTIFICATION] Quote created notification. quoteId={}", quoteId);
    }

    public void notifyQuoteStatusUpdated(String quoteId, String status) {
        log.info(
                "[NOTIFICATION] Quote status updated notification. quoteId={}, status={}",
                quoteId,
                status
        );
    }
}
