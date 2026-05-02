package org.woridge.settings_management.service;

public interface AuditService {
    void log(String action, String target, String status);
}
