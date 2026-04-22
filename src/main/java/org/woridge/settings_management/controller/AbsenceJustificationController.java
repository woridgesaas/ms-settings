package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.absence_justification.ConfigureAbsenceJustificationRequest;
import org.woridge.settings_management.dto.absence_justification.GetAbsenceJustificationResponse;
import org.woridge.settings_management.service.AbsenceJustificationService;

@RestController
@RequestMapping("/absence-justifications")
@AllArgsConstructor
public class AbsenceJustificationController {

    private final AbsenceJustificationService absenceJustificationService;

    @PutMapping
    public ResponseEntity<Void> configureAbsenceJustification(@Valid @RequestBody ConfigureAbsenceJustificationRequest configureAbsenceJustificationRequest) {

        absenceJustificationService.configureAbsenceJustification(configureAbsenceJustificationRequest);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<GetAbsenceJustificationResponse>> getAbsenceJustification() {

        return ResponseEntity.ok(
                SuccessResponse.createSuccessResponse(
                        "Absence justification fetched successfully",
                        absenceJustificationService.getAbsenceJustification()
                )
        );
    }
}
