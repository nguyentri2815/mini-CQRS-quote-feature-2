package com.example.quoteservicecqrs.quote.application.mapper;

import com.example.quoteservicecqrs.quote.application.command.CreateQuoteCommand;
import com.example.quoteservicecqrs.quote.application.dto.QuoteCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class QuoteApiMapper {

    public CreateQuoteCommand toCommand(
            QuoteCreateRequest request,
            String createdBy
    ) {
        return new CreateQuoteCommand(
                request.getCustomerName(),
                request.getProductCode(),
                request.getPremium(),
                createdBy
        );
    }
}
