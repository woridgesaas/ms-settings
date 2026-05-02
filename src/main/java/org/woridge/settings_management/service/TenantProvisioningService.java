package org.woridge.settings_management.service;

public interface TenantProvisioningService {

    void runMigrationsForTenant(String subdomain, String serviceKey);
}
