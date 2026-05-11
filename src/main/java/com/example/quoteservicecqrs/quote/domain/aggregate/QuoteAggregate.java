package com.example.quoteservicecqrs.quote.domain.aggregate;

import com.example.quoteservicecqrs.common.exception.BusinessException;
import com.example.quoteservicecqrs.quote.application.command.ApproveQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.CreateQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.SubmitQuoteCommand;
import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import com.example.quoteservicecqrs.quote.domain.model.QuoteStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class QuoteAggregate {

    private String id;
    private QuoteStatus status;

    // Xử lý command
    public QuoteCreatedEvent create(CreateQuoteCommand command) {
        this.id = UUID.randomUUID().toString();
        this.status = QuoteStatus.DRAFT;

        return new QuoteCreatedEvent(
                this.id,
                command.getCreatedBy(),
                LocalDateTime.now()
        );
    }

    public QuoteSubmittedEvent submit(SubmitQuoteCommand command) {
        if (this.status != QuoteStatus.DRAFT) {
            throw new BusinessException(
                    "Only DRAFT quote can be submitted. Current status: " + this.status
            );
        }

        return new QuoteSubmittedEvent(
                this.id,
                command.getSubmittedBy(),
                LocalDateTime.now()
        );
    }

    public QuoteApprovedEvent approve(ApproveQuoteCommand command) {
        if (this.status != QuoteStatus.SUBMITTED) {
            throw new BusinessException(
                    "Only SUBMITTED quote can be approved. Current status: " + this.status
            );
        }

        return new QuoteApprovedEvent(
                this.id,
                command.getApprovedBy(),
                LocalDateTime.now()
        );
    }

    //apply event
    public void apply(QuoteCreatedEvent event) {
        this.id = event.getQuoteId();
        this.status = QuoteStatus.DRAFT;
    }

    public void apply(QuoteSubmittedEvent event) {
        this.status = QuoteStatus.SUBMITTED;
    }

    public void apply(QuoteApprovedEvent event) {
        this.status = QuoteStatus.APPROVED;
    }

    public String getId() {
        return id;
    }

    public QuoteStatus getStatus() {
        return status;
    }
}
