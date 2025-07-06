package io.github.tundeadetunji.admin_service.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "app.exchange";
    public static final String ADMIN_QUEUE = "admin.notifications";
    public static final String ADMIN_ROUTING_KEY = "admin.notify";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue adminQueue() {
        return new Queue(ADMIN_QUEUE);
    }

    @Bean
    public Binding adminBinding() {
        return BindingBuilder.bind(adminQueue()).to(topicExchange()).with(ADMIN_ROUTING_KEY);
    }
}
