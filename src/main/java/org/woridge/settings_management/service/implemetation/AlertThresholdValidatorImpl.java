package org.woridge.settings_management.service.implemetation;

import org.springframework.stereotype.Service;
import org.woridge.settings_management.dto.alert_threshold.ConfigureAlertThresholdRequest;
import org.woridge.settings_management.exception.alert_threshold.CriticalThresholdLessThanWarningThresholdException;
import org.woridge.settings_management.model.AlertThreshold;
import org.woridge.settings_management.service.AlertThresholdValidator;

@Service
public class AlertThresholdValidatorImpl implements AlertThresholdValidator {

    @Override
    public void validateWarningThresholdGreaterThanCriticalThreshold(int warningThreshold, int criticalThreshold) {

        if (warningThreshold >= criticalThreshold) {

            throw new CriticalThresholdLessThanWarningThresholdException("Critical threshold must be greater than warning threshold");
        }
    }

    @Override
    public boolean isAlertThresholdUnchanged(AlertThreshold currentAlertThreshold, ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        return currentAlertThreshold.getWarningThreshold() == configureAlertThresholdRequest.warningThreshold()
                && currentAlertThreshold.getCriticalThreshold() == configureAlertThresholdRequest.criticalThreshold();
    }
}
