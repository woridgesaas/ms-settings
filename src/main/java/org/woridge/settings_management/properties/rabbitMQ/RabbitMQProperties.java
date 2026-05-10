package org.woridge.settings_management.properties.rabbitMQ;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQProperties {

    private Exchange exchange;

    private Routing routing;

    private Queue queue;
}