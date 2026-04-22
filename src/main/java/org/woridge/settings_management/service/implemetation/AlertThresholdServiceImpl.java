package org.woridge.settings_management.service.implemetation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.settings_management.dto.alert_threshold.ConfigureAlertThresholdRequest;
import org.woridge.settings_management.dto.alert_threshold.GetAlertThresholdResponse;
import org.woridge.settings_management.exception.alert_threshold.AlertThresholdNotConfiguredException;
import org.woridge.settings_management.mapper.AlertThresholdMapper;
import org.woridge.settings_management.model.AlertThreshold;
import org.woridge.settings_management.repository.AlertThresholdRepository;
import org.woridge.settings_management.service.AlertThresholdService;
import org.woridge.settings_management.service.AlertThresholdValidator;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AlertThresholdServiceImpl implements AlertThresholdService {

    private final AlertThresholdRepository alertThresholdRepository;
    private final AlertThresholdValidator alertThresholdValidator;

    @Override
    public void configureAlertThreshold(ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        alertThresholdValidator.validateWarningThresholdGreaterThanCriticalThreshold(
                configureAlertThresholdRequest.warningThreshold(),
                configureAlertThresholdRequest.criticalThreshold()
        );

        Optional<AlertThreshold> currentAlertThreshold = alertThresholdRepository.findFirstBy();

        currentAlertThreshold.ifPresentOrElse(
                alertThreshold -> updateAlertThreshold(alertThreshold, configureAlertThresholdRequest),
                () -> addAlertThreshold(configureAlertThresholdRequest)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public GetAlertThresholdResponse getAlertThreshold() {

        AlertThreshold currentAlertThreshold = alertThresholdRepository.findFirstBy().orElseThrow(() ->
                new AlertThresholdNotConfiguredException("Alert threshold not configured"));

        return AlertThresholdMapper.toGetAlertThresholdResponse(currentAlertThreshold);
    }

    // Helper method
    private void addAlertThreshold(ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        alertThresholdRepository.save(AlertThresholdMapper.toAlertThreshold(configureAlertThresholdRequest));
    }

    private void updateAlertThreshold(AlertThreshold currentAlertThreshold, ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        if (alertThresholdValidator.isAlertThresholdUnchanged(currentAlertThreshold, configureAlertThresholdRequest)) {

            return;
        }

        AlertThresholdMapper.updateAlertThreshold(currentAlertThreshold, configureAlertThresholdRequest);
    }
}
