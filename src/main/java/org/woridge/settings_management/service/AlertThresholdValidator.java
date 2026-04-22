package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.alert_threshold.ConfigureAlertThresholdRequest;
import org.woridge.settings_management.model.AlertThreshold;

public interface AlertThresholdValidator {

    void validateWarningThresholdGreaterThanCriticalThreshold(int warningThreshold, int criticalThreshold);

    boolean isAlertThresholdUnchanged(AlertThreshold currentAlertThreshold, ConfigureAlertThresholdRequest configureAlertThresholdRequest);
}