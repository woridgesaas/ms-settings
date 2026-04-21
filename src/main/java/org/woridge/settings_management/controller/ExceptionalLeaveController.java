package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.exceptional_leave.GetAllExceptionalLeavesResponse;
import org.woridge.settings_management.dto.exceptional_leave.UpdateExceptionalLeavesRequest;
import org.woridge.settings_management.service.ExceptionalLeaveService;

import java.util.List;

@RestController
@RequestMapping("/exceptional-leaves")
@AllArgsConstructor
public class ExceptionalLeaveController {

    private final ExceptionalLeaveService exceptionalLeaveService;

    @PutMapping
    public ResponseEntity<SuccessResponse<Void>> updateExceptionalLeaves(@Valid @RequestBody UpdateExceptionalLeavesRequest updateExceptionalLeavesRequest) {

        exceptionalLeaveService.updateExceptionalLeaves(updateExceptionalLeavesRequest);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(
                        SuccessResponse.createSuccessResponse(
                                "Exceptional leaves updated successfully",
                                null
                        )
                );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<GetAllExceptionalLeavesResponse>>> getAllExceptionalLeaves() {

        return ResponseEntity.ok(
                SuccessResponse.createSuccessResponse(
                        "Exceptional leaves fetched successfully",
                        exceptionalLeaveService.getAllExceptionalLeaves()
                )
        );
    }
}
