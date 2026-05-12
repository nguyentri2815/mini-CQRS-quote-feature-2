package com.example.quoteservicecqrs.quote.api;

import com.example.quoteservicecqrs.quote.application.command.ApproveQuoteCommand;
import com.example.quoteservicecqrs.quote.application.command.SubmitQuoteCommand;
import com.example.quoteservicecqrs.quote.application.dto.QuoteCreateRequest;
import com.example.quoteservicecqrs.quote.application.dto.QuoteListItemResponse;
import com.example.quoteservicecqrs.quote.application.dto.QuoteResponse;
import com.example.quoteservicecqrs.quote.application.handler.QuoteCommandHandler;
import com.example.quoteservicecqrs.quote.application.mapper.QuoteApiMapper;
import com.example.quoteservicecqrs.quote.query.QuoteSearchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    private final QuoteCommandHandler quoteCommandHandler;
    private final QuoteApiMapper quoteApiMapper;
    private final QuoteSearchService quoteSearchService;

    public QuoteController(
            QuoteCommandHandler quoteCommandHandler,
            QuoteApiMapper quoteApiMapper,
            QuoteSearchService quoteSearchService
    ) {
        this.quoteCommandHandler = quoteCommandHandler;
        this.quoteApiMapper = quoteApiMapper;
        this.quoteSearchService = quoteSearchService;
    }

    @PostMapping
    public QuoteResponse create(@Valid @RequestBody QuoteCreateRequest request) {
        String currentUser = "demo-user";

        return quoteCommandHandler.handle(
                quoteApiMapper.toCommand(request, currentUser)
        );
    }

    @PostMapping("/{id}/submit")
    public QuoteResponse submit(@PathVariable String id) {
        String currentUser = "demo-user";

        SubmitQuoteCommand command = new SubmitQuoteCommand(id, currentUser);

        return quoteCommandHandler.handle(command);
    }

    @PostMapping("/{id}/approve")
    public QuoteResponse approve(@PathVariable String id) {
        String currentUser = "demo-user";

        ApproveQuoteCommand command = new ApproveQuoteCommand(id, currentUser);

        return quoteCommandHandler.handle(command);
    }

    @GetMapping
    public List<QuoteListItemResponse> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status
    ) {
        return quoteSearchService.list(keyword, status);
    }

}
