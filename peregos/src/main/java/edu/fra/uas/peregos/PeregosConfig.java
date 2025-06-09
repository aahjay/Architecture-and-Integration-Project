package edu.fra.uas.peregos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PeregosConfig {

    private static final Logger log = LoggerFactory.getLogger(PeregosConfig.class);

    @Bean
    public Queue peregosRequestQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        return new Queue("peregos.request.queue", true, false, false, args);
    }

    @Bean
    public Queue peregosResponseQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
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

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        template.setConfirmCallback((correlation, ack, reason) -> {
            if (!ack) {
                log.error("Message not acknowledged: {}", reason);
            }
        });
        template.setMandatory(true);
        template.setBeforePublishPostProcessors(message -> {
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
        return template;
    }
}
