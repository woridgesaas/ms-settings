package org.woridge.settings_management.mapper;

import org.woridge.settings_management.dto.alert_threshold.ConfigureAlertThresholdRequest;
import org.woridge.settings_management.dto.alert_threshold.GetAlertThresholdResponse;
import org.woridge.settings_management.model.AlertThreshold;

public class AlertThresholdMapper {

    public static AlertThreshold toAlertThreshold(ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        return AlertThreshold
                .builder()
                .warningThreshold(configureAlertThresholdRequest.warningThreshold())
                .criticalThreshold(configureAlertThresholdRequest.criticalThreshold())
                .build();
    }

    public static void updateAlertThreshold(AlertThreshold currentAlertThreshold, ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        currentAlertThreshold.setWarningThreshold(configureAlertThresholdRequest.warningThreshold());
        currentAlertThreshold.setCriticalThreshold(configureAlertThresholdRequest.criticalThreshold());
    }

    public static GetAlertThresholdResponse toGetAlertThresholdResponse(AlertThreshold currentAlertThreshold) {

        return new GetAlertThresholdResponse(
                currentAlertThreshold.getId(),
                currentAlertThreshold.getWarningThreshold(),
                currentAlertThreshold.getCriticalThreshold()
        );
    }
}
