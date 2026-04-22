package org.woridge.settings_management.service;

import org.woridge.settings_management.dto.alert_threshold.ConfigureAlertThresholdRequest;
import org.woridge.settings_management.dto.alert_threshold.GetAlertThresholdResponse;

public interface AlertThresholdService {

    void configureAlertThreshold(ConfigureAlertThresholdRequest configureAlertThresholdRequest);

    GetAlertThresholdResponse getAlertThreshold();
}
