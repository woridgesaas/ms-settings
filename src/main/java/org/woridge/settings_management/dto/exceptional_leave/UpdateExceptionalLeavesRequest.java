package org.woridge.settings_management.dto.exceptional_leave;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateExceptionalLeavesRequest(

        @NotNull(message = "Exceptional leaves list is required")
        @NotEmpty(message = "Exceptional leaves list cannot be empty")
        List<UpdateExceptionalLeaveItem> exceptionalLeaves
) {
}
