package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.annual_leave.ConfigureAnnualBalanceRequest;
import org.woridge.settings_management.dto.annual_leave.GetAnnualBalanceResponse;
import org.woridge.settings_management.service.AnnualLeaveService;

@RestController
@RequestMapping("/annual-leaves")
@AllArgsConstructor
public class AnnualLeaveController {

    private final AnnualLeaveService annualLeaveService;

    @PutMapping
    public ResponseEntity<SuccessResponse<Void>> configureAnnualBalance(@Valid @RequestBody ConfigureAnnualBalanceRequest configureAnnualBalanceRequest) {

        annualLeaveService.configureAnnualBalance(configureAnnualBalanceRequest);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(
                        SuccessResponse.createSuccessResponse(
                                "Annual balance configured successfully",
                                null
                        )
                );
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<GetAnnualBalanceResponse>> getAnnualBalance() {

        return ResponseEntity
                .ok(
                        SuccessResponse.createSuccessResponse(
                                "Annual balance fetched successfully",
                                annualLeaveService.getAnnualBalance()
                        )
                );
    }
}
