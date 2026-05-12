package com.example.quoteservicecqrs.common.event.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuoteEventRabbitConfig {

    public static final String QUOTE_EVENT_EXCHANGE = "quote.event.exchange";

    public static final String QUOTE_WORKFLOW_QUEUE = "quote.workflow.queue";

    public static final String QUOTE_EVENT_ROUTING_KEY = "quote.event";

    @Bean
    public TopicExchange quoteEventExchange() {
        return new TopicExchange(QUOTE_EVENT_EXCHANGE);
    }

    @Bean
    public Queue quoteWorkflowQueue() {
        return new Queue(QUOTE_WORKFLOW_QUEUE, true);
    }

    @Bean
    public Binding quoteWorkflowBinding() {
        return BindingBuilder
                .bind(quoteWorkflowQueue())
                .to(quoteEventExchange())
                .with(QUOTE_EVENT_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
