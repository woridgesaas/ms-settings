package org.woridge.settings_management.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.woridge.settings_management.properties.rabbitMQ.RabbitMQProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
    private final RabbitMQProperties properties;

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public TopicExchange woridgeExchange(){
        return ExchangeBuilder
                .topicExchange(this.properties.getExchange().getNotification())
                .durable(true)
                .build();

    }

    @Bean
    public Queue tenantReadyQueue(){
        return QueueBuilder
                .durable(this.properties.getQueue().getProvisioning())
                    .build();
    }

    @Bean
    public Binding provisioningBinding(){
        return BindingBuilder
                .bind(tenantReadyQueue())
                .to(woridgeExchange())
                .with(this.properties.getRouting().getTenantReady());
    }
}
