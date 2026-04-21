package org.woridge.settings_management.dto.annual_leave;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ConfigureAnnualBalanceRequest(

        @NotNull(message = "Annual balance is required")
        @Min(value = 1, message = "Annual balance must be at least 1 day")
        int balance
) {
}
