package org.woridge.settings_management.properties.rabbitMQ;
import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {
    private String exchangeNotification;
    private Routing routing;
    private Queue queue;
}
