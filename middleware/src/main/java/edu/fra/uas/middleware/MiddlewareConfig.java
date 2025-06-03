package edu.fra.uas.middleware;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiddlewareConfig {

    // HIS Configuration
    @Bean
    public Queue hisRequestQueue() {
        return new Queue("his.request.queue", true);
    }

    @Bean
    public Queue hisResponseQueue() {
        return new Queue("his.response.queue", true);
    }

    @Bean
    public DirectExchange hisExchange() {
        return new DirectExchange("his.exchange");
    }

    @Bean
    public Binding bindingHisRequest(Queue hisRequestQueue, DirectExchange hisExchange) {
        return BindingBuilder.bind(hisRequestQueue).to(hisExchange).with("his.request");
    }

    @Bean
    public Binding bindingHisResponse(Queue hisResponseQueue, DirectExchange hisExchange) {
        return BindingBuilder.bind(hisResponseQueue).to(hisExchange).with("his.response");
    }

    // Peregos Configuration
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

    // WyseFlow Configuration
    @Bean
    public Queue wyseflowRequestQueue() {
        return new Queue("wyseflow.request.queue", true);
    }

    @Bean
    public Queue wyseflowResponseQueue() {
        return new Queue("wyseflow.response.queue", true);
    }

    @Bean
    public DirectExchange wyseflowExchange() {
        return new DirectExchange("wyseflow.exchange");
    }

    @Bean
    public Binding bindingWyseflowRequest(Queue wyseflowRequestQueue, DirectExchange wyseflowExchange) {
        return BindingBuilder.bind(wyseflowRequestQueue).to(wyseflowExchange).with("wyseflow.request");
    }

    @Bean
    public Binding bindingWyseflowResponse(Queue wyseflowResponseQueue, DirectExchange wyseflowExchange) {
        return BindingBuilder.bind(wyseflowResponseQueue).to(wyseflowExchange).with("wyseflow.response");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
