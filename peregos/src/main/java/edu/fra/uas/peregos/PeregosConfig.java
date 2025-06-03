package edu.fra.uas.peregos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;

@Configuration
public class PeregosConfig {

    @Bean
    public Queue peregosRequestQueue() {
        return new Queue("peregos.request.queue", true);
    }

    @Bean
    public Queue peregosResponseQueue() {
        return new Queue("peregos.response.queue", true);
    }

    @Bean
    public DirectExchange peregosExchange() {
        return new DirectExchange("peregos.exchange");
    }

    @Bean
    public Binding bindingPeregosRequest(Queue peregosRequestQueue, DirectExchange peregosExchange) {
        return BindingBuilder.bind(peregosRequestQueue).to(peregosExchange).with("peregos.request");
    }

    @Bean
    public Binding bindingPeregosResponse(Queue peregosResponseQueue, DirectExchange peregosExchange) {
        return BindingBuilder.bind(peregosResponseQueue).to(peregosExchange).with("peregos.response");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
