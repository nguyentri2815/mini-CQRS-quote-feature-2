package com.example.quoteservicecqrs.quote.domain.aggregate;

import com.example.quoteservicecqrs.common.exception.BusinessException;
import com.example.quoteservicecqrs.quote.application.command.ApproveQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.CreateQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.SubmitQuoteCommand;
import com.example.quoteservicecqrs.quote.domain.event.QuoteApprovedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteCreatedEvent;
import com.example.quoteservicecqrs.quote.domain.event.QuoteSubmittedEvent;
import com.example.quoteservicecqrs.quote.domain.model.QuoteStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class QuoteAggregate {

    private String id;
    private String quoteNumber;
    private String customerName;
    private String productCode;
    private BigDecimal premium;
    private QuoteStatus status;

    public static QuoteAggregate restore(
            String id,
            String quoteNumber,
            String customerName,
            String productCode,
            BigDecimal premium,
            QuoteStatus status
    ) {
        QuoteAggregate aggregate = new QuoteAggregate();
        aggregate.id = id;
        aggregate.quoteNumber = quoteNumber;
        aggregate.customerName = customerName;
        aggregate.productCode = productCode;
        aggregate.premium = premium;
        aggregate.status = status;
        return aggregate;
    }

    public QuoteCreatedEvent create(CreateQuoteCommand command) {
        this.id = UUID.randomUUID().toString();
        this.quoteNumber = generateQuoteNumber();
        this.customerName = command.getCustomerName();
        this.productCode = command.getProductCode();
        this.premium = command.getPremium();
        this.status = QuoteStatus.DRAFT;

        return new QuoteCreatedEvent(
                this.id,
                command.getCustomerName(),
                command.getProductCode(),
                command.getPremium(),
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

    public void apply(QuoteCreatedEvent event) {
        this.id = event.getQuoteId();
        this.customerName = event.getCustomerName();
        this.productCode = event.getProductCode();
        this.premium = event.getPremium();
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

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductCode() {
        return productCode;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public QuoteStatus getStatus() {
        return status;
    }

    private String generateQuoteNumber() {
        return "Q-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
