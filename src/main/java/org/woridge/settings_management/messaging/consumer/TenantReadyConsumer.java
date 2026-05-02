package org.woridge.settings_management.messaging.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.woridge.settings_management.messaging.event.TenantReadyEvent;
import org.woridge.settings_management.service.TenantProvisioningService;

@Service
@Slf4j
@AllArgsConstructor
public class TenantReadyConsumer {

    private final TenantProvisioningService provisioningService;

    @RabbitListener(queues = "#{rabbitMQProperties.queue.provisioning}")
    public void onMessageRecieve(TenantReadyEvent event){

        this.provisioningService.runMigrationsForTenant(event.getSubdomain() , event.getServiceKey());
        log.info("Recieve a message for tenant {} with service key {}" , event.getSubdomain() , event.getServiceKey());

    }
}
