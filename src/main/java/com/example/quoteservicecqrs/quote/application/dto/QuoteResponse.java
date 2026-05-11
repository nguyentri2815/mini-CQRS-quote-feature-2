package com.example.quoteservicecqrs.quote.application.dto;

public class QuoteResponse {

    private final String id;
    private final String status;

    public QuoteResponse(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
