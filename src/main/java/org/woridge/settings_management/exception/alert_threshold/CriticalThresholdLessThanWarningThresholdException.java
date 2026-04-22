package org.woridge.settings_management.exception.alert_threshold;

public class CriticalThresholdLessThanWarningThresholdException extends RuntimeException {

    public CriticalThresholdLessThanWarningThresholdException(String message) {

        super(message);
    }
}
