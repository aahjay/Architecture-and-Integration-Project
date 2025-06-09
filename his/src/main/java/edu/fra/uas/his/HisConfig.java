package edu.fra.uas.his;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.amqp.core.MessageDeliveryMode;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HisConfig {

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
