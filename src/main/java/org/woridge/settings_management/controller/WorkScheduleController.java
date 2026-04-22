package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.work_schedule.ConfigureWorkScheduleRequest;
import org.woridge.settings_management.dto.work_schedule.GetWorkScheduleResponse;
import org.woridge.settings_management.service.WorkScheduleService;

@RestController
@RequestMapping("/work-schedules")
@AllArgsConstructor
public class WorkScheduleController {

    private final WorkScheduleService workScheduleService;

    @PutMapping
    public ResponseEntity<SuccessResponse<Void>> configureWorkSchedule(@Valid @RequestBody ConfigureWorkScheduleRequest configureWorkScheduleRequest) {

        workScheduleService.configureWorkSchedule(configureWorkScheduleRequest);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<GetWorkScheduleResponse>> getWorkSchedule() {

        return ResponseEntity.ok(
                SuccessResponse.createSuccessResponse(
                        "Work schedule fetched successfully",
                        workScheduleService.getWorkSchedule()
                )
        );
    }
}
