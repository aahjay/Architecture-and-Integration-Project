package edu.fra.uas.his;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class HisConfig {

    @Bean
    public Queue hisRequestQueue() {
        return new Queue("his.request.queue", true);
    }

    @Bean
    public Queue hisResponsQueue() {
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
    public Binding bindingHisResponse(Queue hisResponsQueue, DirectExchange hisExchange) {
        return BindingBuilder.bind(hisResponsQueue).to(hisExchange).with("his.response");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
