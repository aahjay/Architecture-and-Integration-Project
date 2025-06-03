package edu.fra.uas.wyseflow;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WyseFlowConfig {

    @Bean
    public Queue wyseFlowRequestQueue() {
        return new Queue("wyseflow.request.queue", true);
    }

    @Bean
    public Queue wyseFlowResponseQueue() {
        return new Queue("wyseflow.response.queue", true);
    }

    @Bean
    public DirectExchange wyseFlowExchange() {
        return new DirectExchange("wyseflow.exchange");
    }

    @Bean
    public Binding bindingWyseFlowRequest(Queue wyseFlowRequestQueue, DirectExchange wyseFlowExchange) {
        return BindingBuilder.bind(wyseFlowRequestQueue).to(wyseFlowExchange).with("wyseflow.request");
    }

    @Bean
    public Binding bindingWyseFlowResponse(Queue wyseFlowResponseQueue, DirectExchange wyseFlowExchange) {
        return BindingBuilder.bind(wyseFlowResponseQueue).to(wyseFlowExchange).with("wyseflow.response");
    }
}
