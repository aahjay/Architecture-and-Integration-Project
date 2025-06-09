package edu.fra.uas.wyseflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class WyseFlowConfig {

    private static final Logger log = LoggerFactory.getLogger(WyseFlowConfig.class);

    @Bean
    public Queue wyseFlowRequestQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        return new Queue("wyseflow.request.queue", true, false, false, args);
    }

    @Bean
    public Queue wyseFlowResponseQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 86400000);
        return new Queue("wyseflow.response.queue", true, false, false, args);
    }

    @Bean
    public DirectExchange wyseFlowExchange() {
        return new DirectExchange("wyseflow.exchange", true, false);
    }

    @Bean
    public Binding bindingWyseFlowRequest(Queue wyseFlowRequestQueue, DirectExchange wyseFlowExchange) {
        return BindingBuilder.bind(wyseFlowRequestQueue).to(wyseFlowExchange).with("wyseflow.request");
    }

    @Bean
    public Binding bindingWyseFlowResponse(Queue wyseFlowResponseQueue, DirectExchange wyseFlowExchange) {
        return BindingBuilder.bind(wyseFlowResponseQueue).to(wyseFlowExchange).with("wyseflow.response");
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
                log.error("Message not acknowledged: correlationId={}, reason={}", correlation, reason);
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
