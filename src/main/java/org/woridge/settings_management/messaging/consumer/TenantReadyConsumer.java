package org.woridge.settings_management.messaging.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.messaging.event.TenantReadyEvent;

@Service
@Slf4j
@AllArgsConstructor
public class TenantReadyConsumer {

    @RabbitListener(queues = "#{rabbitMQProperties.queue.provisioning}")
    public void onMessageRecieve(TenantReadyEvent event){
        log.info("Recieve a message for tenant {} with service key {}" , event.getSubdomain() , event.getServiceKey());

    }
}
