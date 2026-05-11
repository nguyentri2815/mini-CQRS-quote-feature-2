package com.example.quoteservicecqrs.quote.workflow;

import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import com.example.quoteservicecqrs.quote.intrastructure.allocation.QuoteAllocationGateway;
import com.example.quoteservicecqrs.quote.intrastructure.notification.QuoteNotificationService;
import com.example.quoteservicecqrs.quote.intrastructure.search.service.QuoteIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuoteSyncWorkflow {

    private static final Logger log = LoggerFactory.getLogger(QuoteSyncWorkflow.class);

    private final QuoteIndexService quoteIndexService;
    private final QuoteNotificationService quoteNotificationService;
    private final QuoteAllocationGateway quoteAllocationGateway;

    public QuoteSyncWorkflow(
            QuoteIndexService quoteIndexService,
            QuoteNotificationService quoteNotificationService,
            QuoteAllocationGateway quoteAllocationGateway
    ) {
        this.quoteIndexService = quoteIndexService;
        this.quoteNotificationService = quoteNotificationService;
        this.quoteAllocationGateway = quoteAllocationGateway;
    }

    public void onQuoteCreated(QuoteCreatedEvent event) {
        String quoteId = event.getQuoteId();

        log.info("[WORKFLOW] Handling QuoteCreatedEvent. quoteId={}", quoteId);

        quoteIndexService.syncQuote(quoteId);

        quoteNotificationService.notifyQuoteCreated(quoteId);
    }

    public void onQuoteSubmitted(QuoteSubmittedEvent event) {
        String quoteId = event.getQuoteId();

        log.info("[WORKFLOW] Handling QuoteSubmittedEvent. quoteId={}", quoteId);

        quoteIndexService.syncQuote(quoteId);

        quoteNotificationService.notifyQuoteStatusUpdated(quoteId, "SUBMITTED");

        quoteAllocationGateway.sendSubmitQuoteAllocation(quoteId);
    }

    public void onQuoteApproved(QuoteApprovedEvent event) {
        String quoteId = event.getQuoteId();

        log.info("[WORKFLOW] Handling QuoteApprovedEvent. quoteId={}", quoteId);

        quoteIndexService.syncQuote(quoteId);

        quoteNotificationService.notifyQuoteStatusUpdated(quoteId, "APPROVED");

        quoteAllocationGateway.sendApproveQuoteAllocation(quoteId);
    }
}
