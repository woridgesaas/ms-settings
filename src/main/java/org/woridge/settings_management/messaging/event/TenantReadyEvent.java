package org.woridge.settings_management.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class TenantReadyEvent {
    private int subscriptionId;
    private String subdomain;
    private String serviceKey;
}
