package edu.fra.uas.middleware;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MiddlewareConfig {

    // HIS Configuration
    @Bean
    public Queue hisRequestQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        return new Queue("his.request.queue", true, false, false, args);
    }

    @Bean
    public Queue hisResponseQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        return new Queue("his.response.queue", true, false, false, args);
    }

    @Bean
    public DirectExchange hisExchange() {
        return new DirectExchange("his.exchange", true, false);
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
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000); // 24 hour TTL
        return new Queue("peregos.request.queue", true, false, false, args);
    }

    @Bean
    public Queue peregosResponseQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000); // 24 hour TTL
        return new Queue("peregos.response.queue", true, false, false, args);
    }

    @Bean
    public DirectExchange peregosExchange() {
        return new DirectExchange("peregos.exchange", true, false);
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
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000); // 24 hour TTL
        return new Queue("wyseflow.request.queue", true, false, false, args);
    }

    @Bean
    public Queue wyseflowResponseQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000); // 24 hour TTL
        return new Queue("wyseflow.response.queue", true, false, false, args);
    }

    @Bean
    public DirectExchange wyseflowExchange() {
        return new DirectExchange("wyseflow.exchange", true, false);
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

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setMandatory(true);
        template.setBeforePublishPostProcessors(message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
        return template;
    }
}
