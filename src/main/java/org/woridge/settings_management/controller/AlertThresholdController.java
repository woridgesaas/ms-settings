package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.alert_threshold.ConfigureAlertThresholdRequest;
import org.woridge.settings_management.dto.alert_threshold.GetAlertThresholdResponse;
import org.woridge.settings_management.service.AlertThresholdService;

@RestController
@RequestMapping("alert-thresholds")
@AllArgsConstructor
public class AlertThresholdController {

    private final AlertThresholdService alertThresholdService;

    @PutMapping
    public ResponseEntity<Void> configureAlertThreshold(@Valid @RequestBody ConfigureAlertThresholdRequest configureAlertThresholdRequest) {

        alertThresholdService.configureAlertThreshold(configureAlertThresholdRequest);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<GetAlertThresholdResponse>> getAlertThreshold() {

        return ResponseEntity.ok(
                SuccessResponse.createSuccessResponse(
                        "Alert threshold fetched successfully",
                        alertThresholdService.getAlertThreshold()
                )
        );
    }
}
